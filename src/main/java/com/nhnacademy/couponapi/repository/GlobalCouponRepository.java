package com.nhnacademy.couponapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.couponapi.entity.GlobalCoupon;

public interface GlobalCouponRepository extends JpaRepository<GlobalCoupon, Long> {

	boolean existsByCouponId(long couponId);
}
