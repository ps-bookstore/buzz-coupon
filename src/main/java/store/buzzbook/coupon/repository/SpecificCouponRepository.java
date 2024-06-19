package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.SpecificCoupon;

public interface SpecificCouponRepository extends JpaRepository<SpecificCoupon, Integer> {

	boolean existsByBookId(int bookId);

	boolean existsByCouponPolicyId(int couponPolicyId);

	void deleteByBookId(int bookId);

	void deleteByCouponPolicyId(int couponPolicyId);
}
