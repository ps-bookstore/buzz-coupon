package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;

public record CreateCouponPolicyRequest(

	@NotNull
	String name,

	@NotNull
	String discountType,

	@NotNull
	double discountRate,

	@NotNull
	int discountAmount,

	@NotNull
	int standardPrice,

	@NotNull
	int maxDiscountAmount,

	@NotNull
	int period,

	@NotNull
	ZonedDateTime startDate,

	@NotNull
	ZonedDateTime endDate,

	@NotNull
	String couponType
) {
}
