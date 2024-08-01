package store.buzzbook.coupon.dto.coupon;

import store.buzzbook.coupon.entity.Coupon;

public record CouponStatusResponse(

	String couponCode,

	String createDate,

	String expireDate,

	String status
) {
	public static CouponStatusResponse from(Coupon coupon) {
		return new CouponStatusResponse(
			coupon.getCouponCode(),
			coupon.getCreateDate().toString(),
			coupon.getExpireDate().toString(),
			coupon.getStatus().toString());
	}
}
