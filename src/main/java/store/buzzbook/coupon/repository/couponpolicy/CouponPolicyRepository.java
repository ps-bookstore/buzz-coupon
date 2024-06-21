package store.buzzbook.coupon.repository.couponpolicy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.CouponPolicy;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Integer>, CouponPolicyQuerydslRepository {

	Page<CouponPolicyResponse> findAllBy(Pageable pageable);
}
