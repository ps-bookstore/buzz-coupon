package store.buzzbook.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponRequest;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponResponse;
import store.buzzbook.coupon.service.CategoryCouponService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/policies/categories")
@Tag(name = "카테고리 쿠폰 API", description = "카테고리 쿠폰 API 전체 설명서")
public class CategoryCouponController {

	private final CategoryCouponService categoryCouponService;

	@PostMapping
	@Operation(summary = "카테고리 쿠폰 등록", description = "카테고리 쿠폰을 등록 합니다.")
	public ResponseEntity<CreateCategoryCouponResponse> createCategoryCoupon(
		@Valid @RequestBody CreateCategoryCouponRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryCouponService.createCategoryCoupon(request));
	}
}
