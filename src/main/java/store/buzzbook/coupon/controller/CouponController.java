package store.buzzbook.coupon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.buzzbook.coupon.entity.CouponLog;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

	@GetMapping("/{userId}")
	public ResponseEntity<List<CouponLog>> getCoupons(@PathVariable Long userId) {
		return null;
	}
}
