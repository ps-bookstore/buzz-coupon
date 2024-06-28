package store.buzzbook.coupon.dto.couponpolicy;

import java.time.LocalDate;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponPolicy;

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
