package store.buzzbook.coupon.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.UpdateCouponPolicyRequest;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.dto.coupontype.CreateCouponTypeRequest;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponTypeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons/policies")
@Tag(name = "쿠폰 정책 API", description = "쿠폰 정책 API 전체 설명서")
public class CouponPolicyController {

	private final CouponPolicyService couponPolicyService;
	private final CouponTypeService couponTypeService;

	@GetMapping
	@Transactional(readOnly = true)
	@Operation(summary = "쿠폰 정책 리스트 조회", description = "페이징 처리된 쿠폰 정책 리스트를 조회합니다.")
	public ResponseEntity<Page<CouponPolicyResponse>> getCouponPoliciesByPaging(Pageable pageable) {
		return ResponseEntity.ok(couponPolicyService.getCouponPoliciesByPaging(pageable));
	}

	@PostMapping
	@Operation(summary = "쿠폰 정책 생성", description = "쿠폰 정책을 생성합니다.")
	public ResponseEntity<CreateCouponPolicyResponse> createCouponPolicy(CreateCouponPolicyRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(couponPolicyService.createCouponPolicy(request));
	}

	@PutMapping("/{couponPolicyId}")
	@Operation(summary = "쿠폰 정책 수정", description = "쿠폰 정책의 다운로드가 끝나는 날을 수정합니다.")
	public ResponseEntity<CouponPolicyResponse> updateCouponPolicy(@PathVariable int couponPolicyId,
		UpdateCouponPolicyRequest request) {
		return ResponseEntity.ok(couponPolicyService.updateCouponPolicy(couponPolicyId, request));
	}

	@DeleteMapping("/{couponPolicyId}")
	@Operation(summary = "쿠폰 정책 삭제", description = "쿠폰 정책을 삭제합니다.")
	public ResponseEntity<Void> deleteCouponPolicy(@PathVariable int couponPolicyId) {
		couponPolicyService.deleteCouponPolicy(couponPolicyId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/types")
	@Operation(summary = "쿠폰 타입 조회", description = "쿠폰 정책 타입을 조회 합니다.")
	public ResponseEntity<List<CouponTypeResponse>> getCouponTypes() {
		return ResponseEntity.ok(couponTypeService.getAllCouponTypes());
	}

	@PostMapping("/types")
	@Operation(summary = "쿠폰 타입 등록", description = "쿠폰 정책 타입을 등록 합니다.")
	public ResponseEntity<CouponTypeResponse> createCouponType(@Valid @RequestBody CreateCouponTypeRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(couponTypeService.createCouponType(request));
	}

	@DeleteMapping("/types/{couponTypeId}")
	@Operation(summary = "쿠폰 타입 삭제", description = "쿠폰 정책 타입을 삭제 합니다.")
	public ResponseEntity<Void> deleteCouponType(@PathVariable int couponTypeId) {
		couponTypeService.deleteCouponType(couponTypeId);
		return ResponseEntity.noContent().build();
	}
}
