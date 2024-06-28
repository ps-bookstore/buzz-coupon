package store.buzzbook.coupon.repository.couponpolicy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.entity.CouponPolicy;

public interface CouponPolicyQuerydslRepository {

	List<CouponPolicy> findAllByBookId(int bookId);

	Page<CouponPolicy> findAllByCondition(
		Pageable pageable,
		String discountTypeName,
		String isDeleted,
		String couponTypeName);
}
