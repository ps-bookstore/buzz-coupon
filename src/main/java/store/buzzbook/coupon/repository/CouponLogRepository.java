package store.buzzbook.coupon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.NotNull;
import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.entity.CouponLog;
import store.buzzbook.coupon.entity.constant.CouponStatus;

public interface CouponLogRepository extends JpaRepository<CouponLog, Long> {

	Page<CouponLogResponse> findAllByUserIdAndStatus(@NotNull long userId, @NotNull CouponStatus status,
		Pageable pageable);

	boolean existsByCouponPolicyIdAndUserId(int couponPolicyId, long userId);

}
