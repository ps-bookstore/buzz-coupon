package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CouponLog;

public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {
}
