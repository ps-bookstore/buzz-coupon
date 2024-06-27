package store.buzzbook.coupon.dto.coupon;

import java.time.LocalDate;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.Coupon;
import store.buzzbook.coupon.common.constant.CouponStatus;

public record CouponResponse(
	long id,
	LocalDate createDate,
	LocalDate expireDate,
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
