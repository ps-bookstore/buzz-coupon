package com.nhnacademy.couponapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhnacademy.couponapi.entity.Coupon;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

	@GetMapping("/{userId}")
	public ResponseEntity<List<Coupon>> getCoupons(@PathVariable Long userId) {
		return null;
	}
}
