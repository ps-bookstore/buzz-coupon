package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.CategoryCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryCouponRepository extends JpaRepository<CategoryCoupon, Long> {
}
