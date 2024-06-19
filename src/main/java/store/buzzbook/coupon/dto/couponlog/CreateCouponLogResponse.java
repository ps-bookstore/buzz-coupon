package store.buzzbook.coupon.dto.couponlog;

import store.buzzbook.coupon.entity.CouponLog;

public record CreateCouponLogResponse(
	long id,
	long userId
) {
	public static CreateCouponLogResponse from(CouponLog couponLog) {
		return new CreateCouponLogResponse(couponLog.getId(), couponLog.getUserId());
	}
}
