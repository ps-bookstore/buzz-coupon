package store.buzzbook.coupon.dto.coupontype;

import jakarta.validation.constraints.NotNull;

public record CreateCouponTypeRequest(

	@NotNull
	String name
) {
}
