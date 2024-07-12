package store.buzzbook.coupon.dto.coupon;

import java.time.LocalDate;

import store.buzzbook.coupon.common.constant.CouponStatus;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.Coupon;

/**
 * 쿠폰의 응답 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰의 ID, 생성일, 만료일, 상태, 쿠폰 정책 응답 데이터를 포함합니다.
 * </p>
 *
 * @param id 쿠폰의 ID
 * @param createDate 쿠폰 생성일
 * @param expireDate 쿠폰 만료일
 * @param status 쿠폰 상태
 * @param couponPolicyResponse 쿠폰 정책 응답 데이터
 */
public record CouponResponse(
	long id,
	String couponCode,
	LocalDate createDate,
	LocalDate expireDate,
	CouponStatus status,
	CouponPolicyResponse couponPolicyResponse
) {
	/**
	 * Coupon 엔티티를 CouponResponse 객체로 변환합니다.
	 *
	 * @param coupon 변환할 Coupon 엔티티
	 * @return 변환된 CouponResponse 객체
	 */
	public static CouponResponse from(Coupon coupon) {
		return new CouponResponse(
			coupon.getId(),
			coupon.getCouponCode(),
			coupon.getCreateDate(),
			coupon.getExpireDate(),
			coupon.getStatus(),
			CouponPolicyResponse.from(coupon.getCouponPolicy())
		);
	}
}
