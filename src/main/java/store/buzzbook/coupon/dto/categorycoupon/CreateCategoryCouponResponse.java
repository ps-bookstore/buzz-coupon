package store.buzzbook.coupon.dto.categorycoupon;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.CategoryCoupon;

public record CreateCategoryCouponResponse(
	int id,
	int categoryId,
	CouponPolicyResponse couponPolicyResponse
) {
	public static CreateCategoryCouponResponse from(CategoryCoupon categoryCoupon) {
		return new CreateCategoryCouponResponse(
			categoryCoupon.getId(),
			categoryCoupon.getCategoryId(),
			CouponPolicyResponse.from(categoryCoupon.getCouponPolicy())
		);
	}
}
