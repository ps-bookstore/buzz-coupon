package store.buzzbook.coupon.repository.couponpolicy;

import java.util.List;

import org.springframework.data.domain.Page;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyConditionRequest;
import store.buzzbook.coupon.entity.CouponPolicy;

public interface CouponPolicyQuerydslRepository {

	List<CouponPolicy> findAllByBookId(int bookId);

	Page<CouponPolicy> findAllByCondition(CouponPolicyConditionRequest condition);
}
