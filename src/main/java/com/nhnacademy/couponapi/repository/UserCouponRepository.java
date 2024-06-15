package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
