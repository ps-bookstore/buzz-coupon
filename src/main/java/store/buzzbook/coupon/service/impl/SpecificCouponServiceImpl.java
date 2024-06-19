package store.buzzbook.coupon.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponRequest;
import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponResponse;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.repository.SpecificCouponRepository;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.SpecificCouponService;

@Service
@RequiredArgsConstructor
public class SpecificCouponServiceImpl implements SpecificCouponService {

	private final SpecificCouponRepository specificCouponRepository;
	private final CouponPolicyService couponPolicyService;

	@Override
	public CreateSpecificCouponResponse createSpecificCoupon(CreateSpecificCouponRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("특정 도서 쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = couponPolicyService.getCouponPolicyById(request.couponPolicyId());

		SpecificCoupon specificCoupon = SpecificCoupon.builder()
			.couponPolicy(couponPolicy)
			.bookId(request.bookId())
			.build();

		return CreateSpecificCouponResponse.from(specificCouponRepository.save(specificCoupon));
	}

	@Override
	public void deleteSpecificCouponByCouponPolicyId(int couponPolicyId) {
		
	}
}
