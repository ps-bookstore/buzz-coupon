package store.buzzbook.coupon.dto.coupon;

import store.buzzbook.coupon.common.constant.CouponStatus;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.Coupon;

public record CreateCouponResponse(
	long id,
	String couponCode,
	String createDate,
	String expireDate,
	CouponStatus couponStatus,
	CouponPolicyResponse couponPolicyResponse
) {
	public static CreateCouponResponse from(Coupon coupon) {
		return new CreateCouponResponse(
			coupon.getId(),
			coupon.getCouponCode(),
			coupon.getCreateDate().toString(),
			coupon.getExpireDate().toString(),
			coupon.getStatus(),
			CouponPolicyResponse.from(coupon.getCouponPolicy()));
	}
}
