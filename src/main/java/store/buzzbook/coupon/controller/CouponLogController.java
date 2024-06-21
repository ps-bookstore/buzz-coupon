package store.buzzbook.coupon.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.UpdateCouponLogRequest;
import store.buzzbook.coupon.service.CouponLogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/logs")
@Tag(name = "쿠폰 내역 API", description = "쿠폰 내역 API 전체 설명서")
public class CouponLogController {

	private final CouponLogService couponLogService;

	@GetMapping("/{userId}")
	@Transactional(readOnly = true)
	@Operation(summary = "회원 쿠폰 내역 조회", description = "회원의 사용 가능한 쿠폰(status = available) 내역을 조회합니다. ")
	public ResponseEntity<Page<CouponLogResponse>> getCoupons(@PathVariable long userId, Pageable pageable) {
		return ResponseEntity.ok(couponLogService.getCouponLogByPaging(userId, pageable));
	}

	@PostMapping
	@Operation(summary = "회원 쿠폰 내역 등록", description = "회원의 쿠폰 내역을 등록합니다.")
	public ResponseEntity<CreateCouponLogResponse> createCouponLog(@Valid @RequestBody CreateCouponLogRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(couponLogService.createCouponLog(request));
	}

	@PutMapping("/{couponLogId}")
	@Operation(summary = "회원 쿠폰 내역 수정", description = "회원의 쿠폰 내역 상태를 수정합니다.")
	public ResponseEntity<CouponLogResponse> updateCouponLog(@PathVariable long couponLogId,
		@Valid @RequestBody UpdateCouponLogRequest request) {
		return ResponseEntity.ok(couponLogService.updateCouponLog(couponLogId, request));
	}
}
