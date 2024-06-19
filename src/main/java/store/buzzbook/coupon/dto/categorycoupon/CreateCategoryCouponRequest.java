package store.buzzbook.coupon.dto.categorycoupon;

public record CreateCategoryCouponRequest(
	int couponPolicyId,
	int categoryId
) {
}
