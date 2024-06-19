package store.buzzbook.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;

public interface CouponPolicyService {

	Page<CouponPolicyResponse> getCouponPoliciesByPaging(Pageable pageable);

	CreateCouponPolicyResponse createCouponPolicy(CreateCouponPolicyRequest createCouponPolicyRequest);

	void deleteCouponPolicy(int id);
}
