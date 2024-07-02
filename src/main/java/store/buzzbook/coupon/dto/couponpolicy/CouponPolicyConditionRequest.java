package store.buzzbook.coupon.dto.couponpolicy;

import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 쿠폰 정책 조건 검색 요청 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 페이징 정보, 할인 타입, 삭제 여부, 쿠폰 타입을 포함합니다.
 * </p>
 *
 * @param pageable 페이징 정보
 * @param discountTypeName 쿠폰 정책 할인 타입
 * @param isDeleted 쿠폰 정책 삭제 여부
 * @param couponTypeName 쿠폰 정책 타입
 */
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
