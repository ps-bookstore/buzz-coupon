package store.buzzbook.coupon.dto.coupon;

import lombok.Builder;

@Builder
public record CreateUserCouponRequest(

	long userId,
	int couponPolicyId,
	String couponCode
) {
}
