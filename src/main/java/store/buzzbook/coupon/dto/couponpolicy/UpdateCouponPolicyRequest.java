package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;

public record UpdateCouponPolicyRequest(

	@NotNull
	ZonedDateTime endDate
) {
}
