package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.GlobalCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalCouponRepository extends JpaRepository<GlobalCoupon, Long> {
}
