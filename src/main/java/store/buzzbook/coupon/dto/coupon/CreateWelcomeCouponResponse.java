package store.buzzbook.coupon.dto.coupon;

public record CreateWelcomeCouponResponse(

	int resultCode,
	long userId,
	int couponPolicyId
) {
}
