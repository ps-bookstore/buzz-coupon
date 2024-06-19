package store.buzzbook.coupon.dto.couponlog;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record UpdateCouponLogRequest(

	@NotNull
	CouponStatus status
) {
}
