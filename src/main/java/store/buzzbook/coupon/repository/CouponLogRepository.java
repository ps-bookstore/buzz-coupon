package store.buzzbook.coupon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.entity.CouponLog;

public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {

	Page<CouponLogResponse> findAllByUserIdAndStatus(Long userId, String status, Pageable pageable);

	boolean existsByCouponPolicyIdAndUserId(int couponPolicyId, long userId);

}
