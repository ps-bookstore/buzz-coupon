package store.buzzbook.coupon.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.service.impl.CouponPolicyServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/couponpolicies")
public class CouponPolicyController {

	private final CouponPolicyServiceImpl couponPolicyService;

	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<Page<CouponPolicyResponse>> getCouponPoliciesByPaging(Pageable pageable) {
		return ResponseEntity.ok(couponPolicyService.getCouponPoliciesByPaging(pageable));
	}

	@PostMapping
	public ResponseEntity<CreateCouponPolicyResponse> createCouponPolicy(CreateCouponPolicyRequest request) {
		return ResponseEntity.ok(couponPolicyService.createCouponPolicy(request));
	}
}
