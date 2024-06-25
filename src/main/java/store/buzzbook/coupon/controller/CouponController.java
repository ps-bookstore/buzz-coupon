package store.buzzbook.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import store.buzzbook.coupon.dto.couponlog.CouponResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponResponse;
import store.buzzbook.coupon.dto.couponlog.UpdateCouponRequest;
import store.buzzbook.coupon.service.CouponService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/logs")
@Tag(name = "쿠폰 내역 API", description = "쿠폰 내역 API 전체 설명서")
public class CouponController {

	private final CouponService couponService;

	@PostMapping
	@Operation(summary = "회원 쿠폰 내역 등록", description = "회원의 쿠폰 내역을 등록합니다.")
	public ResponseEntity<CreateCouponResponse> createCouponLog(@Valid @RequestBody CreateCouponRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(couponService.createCoupon(request));
	}

	@PutMapping("/{couponLogId}")
	@Operation(summary = "회원 쿠폰 내역 수정", description = "회원의 쿠폰 내역 상태를 수정합니다.")
	public ResponseEntity<CouponResponse> updateCouponLog(@PathVariable long couponLogId,
		@Valid @RequestBody UpdateCouponRequest request) {
		return ResponseEntity.ok(couponService.updateCoupon(couponLogId, request));
	}
}
