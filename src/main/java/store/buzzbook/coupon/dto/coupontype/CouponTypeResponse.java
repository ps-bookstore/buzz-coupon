package store.buzzbook.coupon.dto.coupontype;

import store.buzzbook.coupon.entity.CouponType;

public record CouponTypeResponse(
	int id,
	String name
) {
	public static CouponTypeResponse from(CouponType couponType) {
		return new CouponTypeResponse(couponType.getId(), couponType.getName().toString());
	}
}
