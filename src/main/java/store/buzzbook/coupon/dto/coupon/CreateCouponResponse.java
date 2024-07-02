package store.buzzbook.coupon.dto.coupon;

import store.buzzbook.coupon.common.constant.CouponStatus;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.Coupon;

/**
 * 쿠폰 생성 응답 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 생성된 쿠폰의 ID, 코드, 생성일, 만료일, 상태, 쿠폰 정책 응답 데이터를 포함합니다.
 * </p>
 *
 * @param id 쿠폰의 ID
 * @param couponCode 쿠폰 코드
 * @param createDate 쿠폰 생성일
 * @param expireDate 쿠폰 만료일
 * @param couponStatus 쿠폰 상태
 * @param couponPolicyResponse 쿠폰 정책 응답 데이터
 */
public record CreateCouponResponse(
	long id,
	String couponCode,
	String createDate,
	String expireDate,
	CouponStatus couponStatus,
	CouponPolicyResponse couponPolicyResponse
) {
	/**
	 * Coupon 엔티티를 CreateCouponResponse 객체로 변환합니다.
	 *
	 * @param coupon 변환할 Coupon 엔티티
	 * @return 변환된 CreateCouponResponse 객체
	 */
	public static CreateCouponResponse from(Coupon coupon) {
		return new CreateCouponResponse(
			coupon.getId(),
			coupon.getCouponCode(),
			coupon.getCreateDate().toString(),
			coupon.getExpireDate().toString(),
			coupon.getStatus(),
			CouponPolicyResponse.from(coupon.getCouponPolicy()));
	}
}
