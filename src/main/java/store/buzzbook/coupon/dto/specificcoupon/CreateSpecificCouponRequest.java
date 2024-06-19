package store.buzzbook.coupon.dto.specificcoupon;

import jakarta.validation.constraints.Min;

public record CreateSpecificCouponRequest(

	@Min(1)
	int couponPolicyId,

	@Min(1)
	int bookId
) {
}
