package store.buzzbook.coupon.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.repository.CouponPolicyRepository;
import store.buzzbook.coupon.service.CouponPolicyService;

@Service
@RequiredArgsConstructor
public class CouponPolicyServiceImpl implements CouponPolicyService {

	private final CouponPolicyRepository couponPolicyRepository;

	public Page<CouponPolicyResponse> getCouponPoliciesByPaging(Pageable pageable) {
		return couponPolicyRepository.findAllBy(pageable);
	}
}