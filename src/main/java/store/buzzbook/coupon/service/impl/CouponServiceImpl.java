package store.buzzbook.coupon.service.impl;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.common.exception.CouponNotFoundException;
import store.buzzbook.coupon.dto.coupon.CouponResponse;
import store.buzzbook.coupon.dto.coupon.CreateCouponRequest;
import store.buzzbook.coupon.dto.coupon.CreateCouponResponse;
import store.buzzbook.coupon.dto.coupon.UpdateCouponRequest;
import store.buzzbook.coupon.entity.Coupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.constant.CouponStatus;
import store.buzzbook.coupon.repository.CouponRepository;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponService;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;
	private final CouponPolicyService couponPolicyService;

	@Override
	public CouponResponse getCoupon(long id) {
		validateId(id);

		Coupon coupon = couponRepository.findById(id).orElseThrow(CouponNotFoundException::new);

		return CouponResponse.from(coupon);
	}

	@Override
	public CreateCouponResponse createCoupon(CreateCouponRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 로그 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = couponPolicyService.getCouponPolicyById(request.couponPolicyId());
		ZonedDateTime now = ZonedDateTime.now();

		Coupon coupon = Coupon.builder()
			.createDate(now)
			.expireDate(now.plusDays(couponPolicy.getPeriod()))
			.status(CouponStatus.AVAILABLE)
			.couponPolicy(couponPolicy)
			.build();

		return CreateCouponResponse.from(couponRepository.save(coupon));
	}

	@Override
	public CouponResponse updateCoupon(long id, UpdateCouponRequest request) {
		validateId(id);

		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 로그 수정 요청을 찾을 수 없습니다.");
		}

		Coupon coupon = couponRepository.findById(id)
			.orElseThrow(CouponNotFoundException::new);

		coupon.changeStatus(request.status());

		return CouponResponse.from(coupon);
	}

	private void validateId(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 요청입니다.");
		}
	}
}
