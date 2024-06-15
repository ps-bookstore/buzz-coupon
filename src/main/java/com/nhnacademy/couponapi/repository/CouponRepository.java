package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
