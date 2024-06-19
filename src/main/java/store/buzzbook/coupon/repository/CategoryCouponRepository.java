package store.buzzbook.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CategoryCoupon;

public interface CategoryCouponRepository extends JpaRepository<CategoryCoupon, Integer> {

	boolean existsByCategoryId(int categoryId);

	boolean existsByCouponPolicyId(int couponPolicyId);

	void deleteByCouponPolicyId(int couponPolicyId);

	void deleteByCategoryId(int categoryId);
}
