package store.buzzbook.coupon.common.service;

import store.buzzbook.coupon.dto.coupon.CreateWelcomeCouponResponse;

public interface CouponProducerService {

	void sendWelcomeCouponResponse(CreateWelcomeCouponResponse response);
}
