package store.buzzbook.coupon.dto.couponpolicy;

import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CouponPolicyConditionRequest(

	@NotNull(message = "페이지가 null 일 수 없습니다.")
	Pageable pageable,

	@NotBlank(message = "쿠폰 정책 할인 타입은 null 일 수 없습니다.")
	String discountTypeName,

	@NotBlank(message = "쿠폰 정책 삭제 여부는 null 일 수 없습니다.")
	String isDeleted,

	@NotBlank(message = "쿠폰 정책 타입은 null 일 수 없습니다.")
	String couponTypeName
) {
}
