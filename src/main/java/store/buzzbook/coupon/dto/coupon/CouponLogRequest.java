package store.buzzbook.coupon.dto.coupon;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 쿠폰 로그 요청 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰 번호, 쿠폰 정책 번호, 쿠폰 상태 이름을 포함합니다.
 * </p>
 *
 * @param couponCode 쿠폰 번호
 * @param couponPolicyId 쿠폰 정책 번호
 */
public record CouponLogRequest(

	@NotBlank(message = "쿠폰 번호는 빈 칸일 수 없습니다.")
	String couponCode,

	@Min(value = 1, message = "쿠폰 정책 번호는 1 보다 작을 수 없습니다.")
	int couponPolicyId

) {
}
