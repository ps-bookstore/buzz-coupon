package store.buzzbook.coupon.service;

import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponRequest;
import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponResponse;

public interface SpecificCouponService {

	CreateSpecificCouponResponse createSpecificCoupon(CreateSpecificCouponRequest request);

	void deleteSpecificCouponByCouponPolicyId(int couponPolicyId);
}
