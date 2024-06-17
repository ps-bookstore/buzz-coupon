package store.buzzbook.coupon.repository;

import store.buzzbook.coupon.entity.CouponPolicy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Long> {
}
