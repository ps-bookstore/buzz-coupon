package store.buzzbook.coupon.dto.couponlog;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record UpdateCouponRequest(

	@NotNull(message = "status 는 null 일 수 없습니다.")
	CouponStatus status
) {
}
