package store.buzzbook.coupon.common.listener;

import static store.buzzbook.coupon.common.constant.CouponPolicyConstant.*;

import java.time.LocalDate;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class CouponListener implements ApplicationListener<ApplicationReadyEvent> {

	private final CouponTypeRepository couponTypeRepository;
	private final CouponPolicyRepository couponPolicyRepository;

	@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		String action = "Created coupon type {}";
		if (!couponTypeRepository.existsByName(CouponRange.GLOBAL)) {
			couponTypeRepository.save(CouponType.builder()
				.name(CouponRange.GLOBAL)
				.build());
			log.debug(action, CouponRange.GLOBAL);
		}

		if (!couponTypeRepository.existsByName(CouponRange.BOOK)) {
			couponTypeRepository.save(CouponType.builder()
				.name(CouponRange.BOOK)
				.build());
			log.debug(action, CouponRange.BOOK);
		}

		if (!couponTypeRepository.existsByName(CouponRange.CATEGORY)) {
			couponTypeRepository.save(CouponType.builder()
				.name(CouponRange.CATEGORY)
				.build());
			log.debug(action, CouponRange.CATEGORY);
		}

		CouponType globalType = couponTypeRepository.findAllByName(CouponRange.GLOBAL)
			.orElseThrow(CouponTypeNotFoundException::new);

		if (!couponPolicyRepository.existsByName(WELCOME_COUPON_POLICY_NAME)) {
			couponPolicyRepository.save(CouponPolicy.builder()
				.name(WELCOME_COUPON_POLICY_NAME)
				.discountType(DiscountType.AMOUNT)
				.discountRate(0)
				.discountAmount(10000)
				.maxDiscountAmount(0)
				.standardPrice(50000)
				.startDate(LocalDate.EPOCH)
				.endDate(LocalDate.of(2099, 12, 31))
				.period(30)
				.isDeleted(false)
				.couponType(globalType)
				.build());
		}
		
		if (!couponPolicyRepository.existsByName(BIRTHDAY_COUPON_POLICY_NAME)) {
			couponPolicyRepository.save(CouponPolicy.builder()
				.name(BIRTHDAY_COUPON_POLICY_NAME)
				.discountType(DiscountType.RATE)
				.discountRate(0.2)
				.discountAmount(0)
				.maxDiscountAmount(10000)
				.standardPrice(20000)
				.startDate(LocalDate.EPOCH)
				.endDate(LocalDate.of(2099, 12, 31))
				.period(0)
				.isDeleted(false)
				.couponType(globalType)
				.build());
		}
	}
}
