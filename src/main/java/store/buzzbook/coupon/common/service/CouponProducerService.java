package store.buzzbook.coupon.common.service;

import store.buzzbook.coupon.dto.coupon.CreateUserCouponRequest;

public interface CouponProducerService {

	void sendWelcomeCouponResponse(CreateUserCouponRequest response);
}
