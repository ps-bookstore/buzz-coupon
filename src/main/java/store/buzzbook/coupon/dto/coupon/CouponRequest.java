package store.buzzbook.coupon.dto.coupon;

import jakarta.validation.constraints.NotBlank;

public record CouponRequest(

	@NotBlank
	String couponCode
) {
}
