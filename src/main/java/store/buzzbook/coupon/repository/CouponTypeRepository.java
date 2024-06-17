package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CouponType;

public interface CouponTypeRepository extends JpaRepository<CouponType, Long> {
}
