package com.nhnacademy.couponapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.couponapi.entity.SpecificCoupon;

public interface SpecificCouponRepository extends JpaRepository<SpecificCoupon, Long> {

	boolean existsByCouponId(long couponId);

	boolean existsByBookId(long bookId);
}
