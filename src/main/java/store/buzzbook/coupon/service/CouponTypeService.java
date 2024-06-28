package store.buzzbook.coupon.service;

import java.util.List;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;

public interface CouponTypeService {

	List<CouponTypeResponse> getAllCouponTypes();

	CouponType getCouponType(String name);
}
