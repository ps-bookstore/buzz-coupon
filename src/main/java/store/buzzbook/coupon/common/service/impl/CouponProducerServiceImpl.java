package store.buzzbook.coupon.common.service.impl;

import static store.buzzbook.coupon.common.config.RabbitmqConfig.*;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.service.CouponProducerService;
import store.buzzbook.coupon.dto.coupon.CreateWelcomeCouponResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponProducerServiceImpl implements CouponProducerService {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void sendWelcomeCouponResponse(CreateWelcomeCouponResponse response) {
		rabbitTemplate.convertAndSend(RESPONSE_EXCHANGE_NAME, RESPONSE_ROUTING_KEY, response);
	}

}
