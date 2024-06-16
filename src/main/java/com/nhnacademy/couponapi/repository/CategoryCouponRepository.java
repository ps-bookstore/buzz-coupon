package com.nhnacademy.couponapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.couponapi.entity.CategoryCoupon;

public interface CategoryCouponRepository extends JpaRepository<CategoryCoupon, Long> {

	boolean existsByCouponId(long couponId);

	boolean existsByCategoryId(long categoryId);
}
