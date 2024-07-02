package store.buzzbook.coupon.dto.coupon;

import java.util.List;

import lombok.Builder;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;

@Builder
public record CouponPoliciesResponse(
	List<CouponPolicyResponse> globalCouponPolicies,
	List<CouponPolicyResponse> specificCouponPolicies,
	List<CouponPolicyResponse> categoryCouponPolicies
) {
}
