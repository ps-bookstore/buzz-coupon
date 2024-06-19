package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCouponPolicyRequest(

	@NotNull
	String name,

	@NotNull
	String discountType,

	@Min(0)
	@Max(1)
	double discountRate,

	@Min(0)
	int discountAmount,

	@Min(0)
	int standardPrice,

	@Min(0)
	int maxDiscountAmount,

	@Min(0)
	int period,

	@NotNull
	ZonedDateTime startDate,

	@NotNull
	ZonedDateTime endDate,

	@NotNull
	String couponType
) {
}
