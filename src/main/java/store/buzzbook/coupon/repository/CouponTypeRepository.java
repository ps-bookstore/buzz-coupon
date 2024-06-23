package store.buzzbook.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;

public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {

	List<CouponTypeResponse> findAllBy();

	Optional<CouponType> findAllByName(CouponRange name);
}
