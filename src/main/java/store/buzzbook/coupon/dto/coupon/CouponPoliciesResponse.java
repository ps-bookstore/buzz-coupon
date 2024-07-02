package store.buzzbook.coupon.dto.coupon;

import java.util.List;

import lombok.Builder;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;

/**
 * 다양한 범위의 쿠폰 정책 응답을 담는 클래스입니다.
 * <p>
 * 이 클래스는 글로벌, 특정, 카테고리 쿠폰 정책 리스트를 포함합니다.
 * </p>
 *
 * @param globalCouponPolicies 글로벌 쿠폰 정책 리스트
 * @param specificCouponPolicies 특정 쿠폰 정책 리스트
 * @param categoryCouponPolicies 카테고리 쿠폰 정책 리스트
 */
@Builder
public record CouponPoliciesResponse(
	List<CouponPolicyResponse> globalCouponPolicies,
	List<CouponPolicyResponse> specificCouponPolicies,
	List<CouponPolicyResponse> categoryCouponPolicies
) {
}
