package store.buzzbook.coupon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.dto.coupontype.CreateCouponTypeRequest;
import store.buzzbook.coupon.service.CouponTypeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/policies/types")
@Tag(name = "쿠폰 타입 API", description = "쿠폰 타입 API 전체 설명서")
public class CouponTypeController {

	private final CouponTypeService couponTypeService;

	@GetMapping
	@Operation(summary = "쿠폰 타입 조회", description = "쿠폰 정책 타입을 조회 합니다.")
	public ResponseEntity<List<CouponTypeResponse>> getCouponTypes() {
		return ResponseEntity.ok(couponTypeService.getAllCouponTypes());
	}

	@PostMapping
	@Operation(summary = "쿠폰 타입 등록", description = "쿠폰 정책 타입을 등록 합니다.")
	public ResponseEntity<CouponTypeResponse> createCouponType(@Valid @RequestBody CreateCouponTypeRequest request) {
		return ResponseEntity.ok(couponTypeService.createCouponType(request));
	}
}
