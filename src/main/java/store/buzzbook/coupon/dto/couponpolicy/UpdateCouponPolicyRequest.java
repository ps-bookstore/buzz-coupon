package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;

public record UpdateCouponPolicyRequest(

	@NotNull(message = "endDate 는 null 일 수 없습니다.")
	ZonedDateTime endDate
) {
}
