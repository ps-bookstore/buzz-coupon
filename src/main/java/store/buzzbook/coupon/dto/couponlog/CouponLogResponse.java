package store.buzzbook.coupon.dto.couponlog;

import java.time.ZonedDateTime;

import store.buzzbook.coupon.entity.CouponLog;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record CouponLogResponse(
	long id,
	ZonedDateTime createDate,
	ZonedDateTime expireDate,
	CouponStatus status,
	long userId,
	CouponPolicy couponPolicy
) {
	public static CouponLogResponse from(CouponLog couponLog) {
		return new CouponLogResponse(
			couponLog.getId(),
			couponLog.getCreateDate(),
			couponLog.getExpireDate(),
			couponLog.getStatus(),
			couponLog.getUserId(),
			couponLog.getCouponPolicy()
		);
	}
}
