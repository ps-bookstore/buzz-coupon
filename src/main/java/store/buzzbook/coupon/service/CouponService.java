package store.buzzbook.coupon.service;

import store.buzzbook.coupon.dto.couponlog.CouponResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponResponse;
import store.buzzbook.coupon.dto.couponlog.UpdateCouponRequest;

public interface CouponService {

	CreateCouponResponse createCoupon(CreateCouponRequest request);

	CouponResponse updateCoupon(long id, UpdateCouponRequest request);
}
