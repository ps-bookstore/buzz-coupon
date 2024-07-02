package store.buzzbook.coupon.dto.coupon;

import jakarta.validation.constraints.Min;

/**
 * 웰컴 쿠폰 생성 요청 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 사용자 ID를 포함합니다. 사용자 ID는 1 이상이어야 합니다.
 * </p>
 *
 * @param userId 사용자 ID
 */
public record CreateWelcomeCouponRequest(

	@Min(value = 1, message = "userId 는 1 이상 이어야 합니다.")
	long userId
) {
}
