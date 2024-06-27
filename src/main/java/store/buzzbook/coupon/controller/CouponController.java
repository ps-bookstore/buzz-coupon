package store.buzzbook.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.coupon.CouponResponse;
import store.buzzbook.coupon.dto.coupon.CreateCouponRequest;
import store.buzzbook.coupon.dto.coupon.CreateCouponResponse;
import store.buzzbook.coupon.dto.coupon.UpdateCouponRequest;
import store.buzzbook.coupon.service.CouponService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/")
@Tag(name = "쿠폰 내역 API", description = "쿠폰 내역 API 전체 설명서")
public class CouponController {

	private final CouponService couponService;

	@GetMapping("/{couponId}")
	@Transactional(readOnly = true)
	@Operation(summary = "쿠폰 조회", description = "쿠폰 정보를 조회합니다.")
	public ResponseEntity<CouponResponse> getCoupon(@PathVariable long couponId) {
		return ResponseEntity.ok(couponService.getCoupon(couponId));
	}

	@PostMapping
	@Transactional
	@Operation(summary = "쿠폰 발급", description = "쿠폰을 발급합니다.")
	public ResponseEntity<CreateCouponResponse> createCoupon(@Valid @RequestBody CreateCouponRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(couponService.createCoupon(request));
	}

	@PutMapping("/{couponId}")
	@Transactional
	@Operation(summary = "쿠폰 수정", description = "쿠폰 상태를 수정합니다.")
	public ResponseEntity<CouponResponse> updateCoupon(@PathVariable long couponId,
		@Valid @RequestBody UpdateCouponRequest request) {
		return ResponseEntity.ok(couponService.updateCoupon(couponId, request));
	}
}
