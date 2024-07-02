package store.buzzbook.coupon.service;

import java.util.List;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;

/**
 * 쿠폰 타입 서비스 인터페이스입니다.
 * <p>
 * 쿠폰 타입의 조회와 관련된 비즈니스 로직을 정의합니다.
 * </p>
 */
public interface CouponTypeService {

	/**
	 * 모든 쿠폰 타입을 조회합니다.
	 *
	 * @return 모든 쿠폰 타입 응답 리스트
	 */
	List<CouponTypeResponse> getAllCouponTypes();

	/**
	 * 쿠폰 타입 이름으로 쿠폰 타입을 조회합니다.
	 *
	 * @param name 쿠폰 타입 이름
	 * @return 조회된 쿠폰 타입 엔티티
	 */
	CouponType getCouponType(String name);
}
