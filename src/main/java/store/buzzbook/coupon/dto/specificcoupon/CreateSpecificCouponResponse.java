package store.buzzbook.coupon.dto.specificcoupon;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.SpecificCoupon;

public record CreateSpecificCouponResponse(
	int id,
	int bookId,
	CouponPolicyResponse couponPolicyResponse
) {
	public static CreateSpecificCouponResponse from(SpecificCoupon specificCoupon) {
		return new CreateSpecificCouponResponse(
			specificCoupon.getId(),
			specificCoupon.getBookId(),
			CouponPolicyResponse.from(specificCoupon.getCouponPolicy())
		);
	}
}
