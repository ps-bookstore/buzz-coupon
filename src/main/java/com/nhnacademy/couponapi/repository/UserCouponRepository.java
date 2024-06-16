package com.nhnacademy.couponapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.couponapi.entity.UserCoupon;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

	List<UserCoupon> findByUserId(Long userId);
}
