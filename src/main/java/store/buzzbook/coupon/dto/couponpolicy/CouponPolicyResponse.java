package store.buzzbook.coupon.dto.couponpolicy;

import java.time.ZonedDateTime;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponPolicy;

public record CouponPolicyResponse(
	int id,
	String name,
	double discountRate,
	int discountAmount,
	int standardPrice,
	int period,
	ZonedDateTime startDate,
	ZonedDateTime endDate,
	boolean isDeleted,
	CouponTypeResponse couponTypeResponse
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
			couponPolicy.isDeleted(),
			CouponTypeResponse.from(couponPolicy.getCouponType())
		);
	}
}
