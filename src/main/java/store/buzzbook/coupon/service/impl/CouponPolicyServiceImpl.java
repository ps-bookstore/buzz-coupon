package store.buzzbook.coupon.service.impl;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.UpdateCouponPolicyRequest;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponPolicyRepository;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponTypeService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponPolicyServiceImpl implements CouponPolicyService {

	private final CouponPolicyRepository couponPolicyRepository;
	private final CouponTypeService couponTypeService;

	public Page<CouponPolicyResponse> getCouponPoliciesByPaging(Pageable pageable) {
		return couponPolicyRepository.findAllBy(pageable);
	}

	@Override
	public CreateCouponPolicyResponse createCouponPolicy(CreateCouponPolicyRequest request) {
		if (Objects.isNull(request)) {
			log.warn("쿠폰 생성 요칭이 null 입니다.");
			throw new IllegalArgumentException("쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponType couponType = couponTypeService.getCouponType(request.couponType());

		CouponPolicy couponPolicy = CouponPolicy.builder()
			.name(request.name())
			.discountType(DiscountType.fromString(request.discountType()))
			.discountRate(request.discountRate())
			.discountAmount(request.discountAmount())
			.standardPrice(request.standardPrice())
			.maxDiscountAmount(request.maxDiscountAmount())
			.period(request.period())
			.startDate(request.startDate())
			.endDate(request.endDate())
			.couponType(couponType)
			.build();

		return CreateCouponPolicyResponse.from(couponPolicyRepository.save(couponPolicy));
	}

	@Override
	public void updateCouponPolicy(int id, UpdateCouponPolicyRequest request) {
		validateId(id);

		if (Objects.isNull(request)) {
			log.warn("쿠폰 수정 요칭이 null 입니다.");
			throw new IllegalArgumentException("쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = couponPolicyRepository.findById(id)
			.orElseThrow(CouponPolicyNotFoundException::new);

		couponPolicy.setEndDate(request.endDate());

		couponPolicyRepository.save(couponPolicy);
	}

	@Override
	public void deleteCouponPolicy(int id) {
		validateId(id);
		couponPolicyRepository.deleteById(id);
	}

	private void validateId(int id) {
		if (id <= 0) {
			log.warn("잘못된 파라미터 값입니다. : {}", id);
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
	}
}