package store.buzzbook.coupon.dto.coupontype;

import store.buzzbook.coupon.entity.CouponType;

/**
 * 쿠폰 타입 응답 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰 타입의 ID와 이름을 포함합니다.
 * </p>
 *
 * @param id 쿠폰 타입의 ID
 * @param name 쿠폰 타입의 이름
 */
public record CouponTypeResponse(
	int id,
	String name
) {
	/**
	 * CouponType 엔티티를 CouponTypeResponse 객체로 변환합니다.
	 *
	 * @param couponType 변환할 CouponType 엔티티
	 * @return 변환된 CouponTypeResponse 객체
	 */
	public static CouponTypeResponse from(CouponType couponType) {
		return new CouponTypeResponse(couponType.getId(), couponType.getName().toString());
	}
}
