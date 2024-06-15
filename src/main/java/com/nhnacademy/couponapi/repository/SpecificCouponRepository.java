package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.SpecificCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificCouponRepository extends JpaRepository<SpecificCoupon, Long> {
}
