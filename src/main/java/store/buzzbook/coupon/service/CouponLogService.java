package store.buzzbook.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogResponse;

public interface CouponLogService {

	Page<CouponLogResponse> getCouponLogByPaging(long userId, Pageable pageable);

	CreateCouponLogResponse createCouponLog(CreateCouponLogRequest request);
	
}
