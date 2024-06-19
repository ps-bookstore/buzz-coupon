package store.buzzbook.coupon.service;

import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponRequest;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponResponse;

public interface CategoryCouponService {

	CreateCategoryCouponResponse createCategoryCoupon(CreateCategoryCouponRequest request);

	void deleteCategoryCouponByCouponPolicyId(int couponPolicyId);

	void deleteCategoryCouponByCategoryId(int categoryId);
}
