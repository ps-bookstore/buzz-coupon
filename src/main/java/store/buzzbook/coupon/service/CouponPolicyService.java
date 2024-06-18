package store.buzzbook.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;

public interface CouponPolicyService {

	Page<CouponPolicyResponse> getCouponPoliciesByPaging(Pageable pageable);

}
