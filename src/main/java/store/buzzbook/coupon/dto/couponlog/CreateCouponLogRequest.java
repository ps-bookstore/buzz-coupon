package store.buzzbook.coupon.dto.couponlog;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record CreateCouponLogRequest(

	@Min(1)
	int couponPolicyId,

	@NotNull
	ZonedDateTime createDate,

	@NotNull
	ZonedDateTime expireDate,

	@NotNull
	CouponStatus status,

	@Min(1L)
	long userId
) {
}
