package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;

public record CouponPolicyResponse(
	int id,
	String name,
	double discountRate,
	int discountAmount,
	int standardPrice,
	int period,
	ZonedDateTime startDate,
	ZonedDateTime endDate,
	CouponType couponType
) {
	public static CouponPolicyResponse from(CouponPolicy couponPolicy) {
		return new CouponPolicyResponse(
			couponPolicy.getId(),
			couponPolicy.getName(),
			couponPolicy.getDiscountRate(),
			couponPolicy.getDiscountAmount(),
			couponPolicy.getStandardPrice(),
			couponPolicy.getMaxDiscountAmount(),
			couponPolicy.getStartDate(),
			couponPolicy.getEndDate(),
			couponPolicy.getCouponType()
		);
	}
}