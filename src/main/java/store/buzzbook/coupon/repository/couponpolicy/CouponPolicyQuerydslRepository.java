package store.buzzbook.coupon.repository.couponpolicy;

import java.util.List;

import store.buzzbook.coupon.entity.CouponPolicy;

public interface CouponPolicyQuerydslRepository {

	List<CouponPolicy> findAllByBookId(int bookId);
}
