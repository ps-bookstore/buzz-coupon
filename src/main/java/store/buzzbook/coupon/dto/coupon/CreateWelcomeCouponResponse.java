package store.buzzbook.coupon.dto.coupon;

import lombok.Builder;

@Builder
public record CreateWelcomeCouponResponse(

	int resultCode,
	long userId,
	long couponId
) {
}
