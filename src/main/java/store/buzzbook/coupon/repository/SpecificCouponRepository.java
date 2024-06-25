package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.SpecificCoupon;

public interface SpecificCouponRepository extends JpaRepository<SpecificCoupon, Integer> {
}
