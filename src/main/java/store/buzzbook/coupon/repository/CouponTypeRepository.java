package store.buzzbook.coupon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CouponType;

public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {

	Optional<CouponType> findByName(String name);
}
