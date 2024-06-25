package store.buzzbook.coupon.dto.coupon;

import jakarta.validation.constraints.Min;

public record CreateCouponRequest(

	@Min(value = 1, message = "쿠폰 정책 ID는 1 이상이어야 합니다.")
	int couponPolicyId
) {
}
