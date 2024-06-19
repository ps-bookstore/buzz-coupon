package store.buzzbook.coupon.service.impl;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponPolicyRepository;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponTypeService;

@Service
@RequiredArgsConstructor
public class CouponPolicyServiceImpl implements CouponPolicyService {

	private final CouponPolicyRepository couponPolicyRepository;
	private final CouponTypeService couponTypeService;

	public Page<CouponPolicyResponse> getCouponPoliciesByPaging(Pageable pageable) {
		return couponPolicyRepository.findAllBy(pageable);
	}

	@Override
	public CreateCouponPolicyResponse createCouponPolicy(CreateCouponPolicyRequest createCouponPolicyRequest) {
		if (Objects.isNull(createCouponPolicyRequest)) {
			throw new IllegalArgumentException("쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponType couponType = couponTypeService.getCouponType(createCouponPolicyRequest.couponType());

		CouponPolicy couponPolicy = CouponPolicy.builder()
			.name(createCouponPolicyRequest.name())
			.discountType(DiscountType.fromString(createCouponPolicyRequest.discountType()))
			.discountRate(createCouponPolicyRequest.discountRate())
			.discountAmount(createCouponPolicyRequest.discountAmount())
			.standardPrice(createCouponPolicyRequest.standardPrice())
			.maxDiscountAmount(createCouponPolicyRequest.maxDiscountAmount())
			.period(createCouponPolicyRequest.period())
			.startDate(createCouponPolicyRequest.startDate())
			.endDate(createCouponPolicyRequest.endDate())
			.couponType(couponType)
			.build();

		couponPolicyRepository.save(couponPolicy);

		return CreateCouponPolicyResponse.from(couponPolicy);
	}

	@Override
	public void deleteCouponPolicy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}

		couponPolicyRepository.deleteById(id);
	}
}