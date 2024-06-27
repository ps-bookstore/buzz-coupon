package store.buzzbook.coupon.service;

import store.buzzbook.coupon.dto.coupon.CouponResponse;
import store.buzzbook.coupon.dto.coupon.CreateCouponRequest;
import store.buzzbook.coupon.dto.coupon.CreateCouponResponse;
import store.buzzbook.coupon.dto.coupon.UpdateCouponRequest;

public interface CouponService {

	CouponResponse getCoupon(long id);

	CreateCouponResponse createCoupon(CreateCouponRequest request);

	CouponResponse updateCoupon(long id, UpdateCouponRequest request);
}
