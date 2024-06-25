package store.buzzbook.coupon.dto.couponlog;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public record CreateCouponRequest(

	@Min(value = 1, message = "쿠폰 정책 ID는 1 이상이어야 합니다.")
	int couponPolicyId,

	@NotNull(message = "createDate 는 null 일 수 없습니다.")
	ZonedDateTime createDate,

	@NotNull(message = "expireDate 는 null 일 수 없습니다.")
	ZonedDateTime expireDate,

	@NotNull(message = "status 는 null 일 수 없습니다.")
	CouponStatus status
) {
}
