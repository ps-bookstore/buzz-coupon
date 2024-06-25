package store.buzzbook.coupon.dto.couponlog;

import java.time.ZonedDateTime;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.Coupon;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record CouponResponse(
	long id,
	ZonedDateTime createDate,
	ZonedDateTime expireDate,
	CouponStatus status,

	CouponPolicyResponse couponPolicyResponse
) {
	public static CouponResponse from(Coupon coupon) {
		return new CouponResponse(
			coupon.getId(),
			coupon.getCreateDate(),
			coupon.getExpireDate(),
			coupon.getStatus(),
			CouponPolicyResponse.from(coupon.getCouponPolicy())
		);
	}
}
