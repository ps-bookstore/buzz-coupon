package store.buzzbook.coupon.dto.couponpolicy;

import java.time.LocalDate;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponPolicy;

/**
 * 쿠폰 정책 응답 데이터를 담는 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰 정책의 ID, 이름, 할인 타입, 할인율, 할인 금액, 기준 가격, 최대 할인 금액, 기간, 시작일, 종료일, 삭제 여부, 쿠폰 타입 응답 데이터를 포함합니다.
 * </p>
 *
 * @param id 쿠폰 정책의 ID
 * @param name 쿠폰 정책의 이름
 * @param discountType 쿠폰 정책의 할인 타입
 * @param discountRate 쿠폰 정책의 할인율
 * @param discountAmount 쿠폰 정책의 할인 금액
 * @param standardPrice 쿠폰 정책의 기준 가격
 * @param maxDiscountAmount 쿠폰 정책의 최대 할인 금액
 * @param period 쿠폰 정책의 기간
 * @param startDate 쿠폰 정책의 시작일
 * @param endDate 쿠폰 정책의 종료일
 * @param isDeleted 쿠폰 정책의 삭제 여부
 * @param couponTypeResponse 쿠폰 정책의 타입 응답 데이터
 */
public record CouponPolicyResponse(
	int id,
	String name,
	String discountType,
	double discountRate,
	int discountAmount,
	int standardPrice,
	int maxDiscountAmount,
	int period,
	LocalDate startDate,
	LocalDate endDate,
	boolean isDeleted,
	CouponTypeResponse couponTypeResponse
) {
	/**
	 * CouponPolicy 엔티티를 CouponPolicyResponse 객체로 변환합니다.
	 *
	 * @param couponPolicy 변환할 CouponPolicy 엔티티
	 * @return 변환된 CouponPolicyResponse 객체
	 */
	public static CouponPolicyResponse from(CouponPolicy couponPolicy) {
		return new CouponPolicyResponse(
			couponPolicy.getId(),
			couponPolicy.getName(),
			couponPolicy.getDiscountType().toString(),
			couponPolicy.getDiscountRate(),
			couponPolicy.getDiscountAmount(),
			couponPolicy.getStandardPrice(),
			couponPolicy.getMaxDiscountAmount(),
			couponPolicy.getPeriod(),
			couponPolicy.getStartDate(),
			couponPolicy.getEndDate(),
			couponPolicy.isDeleted(),
			CouponTypeResponse.from(couponPolicy.getCouponType())
		);
	}
}
