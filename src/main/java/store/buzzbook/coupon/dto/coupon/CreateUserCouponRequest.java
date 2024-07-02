package store.buzzbook.coupon.dto.coupon;

import lombok.Builder;

/**
 * 사용자 쿠폰 생성 요청 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 사용자 ID, 쿠폰 정책 ID, 쿠폰 코드를 포함합니다.
 * </p>
 *
 * @param userId 사용자 ID
 * @param couponPolicyId 쿠폰 정책 ID
 * @param couponCode 쿠폰 코드
 */
@Builder
public record CreateUserCouponRequest(

	long userId,
	int couponPolicyId,
	String couponCode
) {
}
