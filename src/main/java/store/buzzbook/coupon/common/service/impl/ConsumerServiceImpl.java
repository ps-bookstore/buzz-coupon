package store.buzzbook.coupon.common.service.impl;

import static store.buzzbook.coupon.common.config.RabbitmqConfig.*;
import static store.buzzbook.coupon.common.constant.CouponPolicyConstant.*;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.adapter.UserAdapter;
import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.dto.coupon.CreateCouponRequest;
import store.buzzbook.coupon.dto.coupon.CreateCouponResponse;
import store.buzzbook.coupon.dto.coupon.CreateUserCouponRequest;
import store.buzzbook.coupon.dto.coupon.CreateWelcomeCouponRequest;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;
import store.buzzbook.coupon.service.CouponService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl {

	private final CouponPolicyRepository couponPolicyRepository;
	private final UserAdapter userAdapter;
	private final CouponService couponService;
	private final ObjectMapper objectMapper;

	private final RabbitTemplate rabbitTemplate;

	@Transactional
	@RabbitListener(queues = REQUEST_QUEUE_NAME)
	public void receiveWelcomeCouponRequest(Message message) {
		log.info("Received welcome coupon request: {}", message);
		String messageType = (String)message.getMessageProperties().getHeaders().get("type");

		switch (messageType) {
			case "welcome":
				CreateWelcomeCouponRequest welcomeRequest = deserialize(message, CreateWelcomeCouponRequest.class);
				handleWelcomeRequest(welcomeRequest);
				break;
			case "download":
				CreateUserCouponRequest downloadRequest = deserialize(message, CreateUserCouponRequest.class);
				handleDownloadRequest(downloadRequest);
				break;
			default:
				throw new IllegalArgumentException("Unknown message type: " + messageType);
		}
	}

	private <T> T deserialize(Message message, Class<T> clazz) {
		try {
			return objectMapper.readValue(message.getBody(), clazz);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private void handleWelcomeRequest(CreateWelcomeCouponRequest request) {
		log.info("Received welcome coupon request: {}", request);
		CouponPolicy welcomeCouponPolicy = couponPolicyRepository.findByName(WELCOME_COUPON_POLICY_NAME)
			.orElseThrow(CouponPolicyNotFoundException::new);

		CreateCouponResponse savedCoupon = couponService.createCoupon(CreateCouponRequest.builder()
			.couponPolicyId(welcomeCouponPolicy.getId())
			.build());

		userAdapter.createUserCoupon(CreateUserCouponRequest.builder()
			.userId(request.userId())
			.couponPolicyId(savedCoupon.couponPolicyResponse().id())
			.couponCode(savedCoupon.couponCode())
			.build());
	}

	private void handleDownloadRequest(CreateUserCouponRequest request) {
		CouponPolicy couponPolicy = couponPolicyRepository.findById(request.couponPolicyId())
			.orElseThrow(CouponPolicyNotFoundException::new);

		CreateCouponResponse savedCoupon = couponService.createCoupon(CreateCouponRequest.builder()
			.couponPolicyId(couponPolicy.getId())
			.build());

		userAdapter.createUserCoupon(CreateUserCouponRequest.builder()
			.userId(request.userId())
			.couponPolicyId(savedCoupon.couponPolicyResponse().id())
			.couponCode(savedCoupon.couponCode())
			.build());
	}

	@RabbitListener(queues = DLQ_QUEUE_NAME)
	public void handleDlqMessage(CreateWelcomeCouponRequest request) {

		rabbitTemplate.convertAndSend(REQUEST_QUEUE_NAME, REQUEST_ROUTING_KEY, request);
	}
}
