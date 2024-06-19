package store.buzzbook.coupon.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.common.exception.CategoryCouponNotFoundException;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponRequest;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponResponse;
import store.buzzbook.coupon.entity.CategoryCoupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.repository.CategoryCouponRepository;
import store.buzzbook.coupon.service.CategoryCouponService;
import store.buzzbook.coupon.service.CouponPolicyService;

@Service
@RequiredArgsConstructor
public class CategoryCouponServiceImpl implements CategoryCouponService {

	private final CategoryCouponRepository categoryCouponRepository;
	private final CouponPolicyService couponPolicyService;

	@Override
	public CreateCategoryCouponResponse createCategoryCoupon(CreateCategoryCouponRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("카테고리 쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = couponPolicyService.getCouponPolicyById(request.couponPolicyId());

		CategoryCoupon categoryCoupon = CategoryCoupon.builder()
			.couponPolicy(couponPolicy)
			.categoryId(request.categoryId())
			.build();

		return CreateCategoryCouponResponse.from(categoryCouponRepository.save(categoryCoupon));
	}

	@Override
	public void deleteCategoryCouponByCouponPolicyId(int couponPolicyId) {
		validateId(couponPolicyId);

		if (!categoryCouponRepository.existsByCouponPolicyId(couponPolicyId)) {
			throw new CategoryCouponNotFoundException();
		}

		categoryCouponRepository.deleteByCouponPolicyId(couponPolicyId);
	}

	@Override
	public void deleteCategoryCouponByCategoryId(int categoryId) {
		validateId(categoryId);

		if (!categoryCouponRepository.existsByCategoryId(categoryId)) {
			throw new CategoryCouponNotFoundException();
		}

		categoryCouponRepository.deleteByCategoryId(categoryId);
	}

	private void validateId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
	}
}
