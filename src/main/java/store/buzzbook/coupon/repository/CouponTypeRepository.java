package store.buzzbook.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.entity.CouponType;

public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {

	List<CouponType> findAllBy();

	Optional<CouponType> findAllByName(CouponRange name);

	boolean existsByName(CouponRange name);
}
