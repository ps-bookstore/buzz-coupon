package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
