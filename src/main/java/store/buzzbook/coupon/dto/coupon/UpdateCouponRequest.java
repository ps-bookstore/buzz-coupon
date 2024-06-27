package store.buzzbook.coupon.dto.coupon;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.common.constant.CouponStatus;

public record UpdateCouponRequest(

	@NotNull(message = "status 는 null 일 수 없습니다.")
	CouponStatus status
) {
}
