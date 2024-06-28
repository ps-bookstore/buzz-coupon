package store.buzzbook.coupon.dto.couponpolicy;

import jakarta.validation.constraints.NotBlank;

public record CouponPolicyPageRequest(

	@NotBlank(message = "할인 타입이 null 일 수 없습니다.")
	String discountTypeName,

	@NotBlank(message = "삭제 여부가 null 일 수 없습니다.")
	String isDeleted,

	@NotBlank(message = "쿠폰 타입이 null 일 수 없습니다.")
	String couponTypeName
) {
}
