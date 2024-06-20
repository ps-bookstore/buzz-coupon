package store.buzzbook.coupon.dto.coupontype;

import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;

public record CouponTypeResponse(
	int id,
	CouponRange name
) {
	public static CouponTypeResponse from(CouponType couponType) {
		return new CouponTypeResponse(couponType.getId(), couponType.getName());
	}
}
