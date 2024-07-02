package store.buzzbook.coupon.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.common.constant.CouponScope;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.service.CouponTypeService;

/**
 * 쿠폰 타입 서비스 구현 클래스입니다.
 * <p>
 * 쿠폰 타입의 조회와 관련된 비즈니스 로직을 처리합니다.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {

	private final CouponTypeRepository couponTypeRepository;

	/**
	 * 모든 쿠폰 타입을 조회합니다.
	 *
	 * @return 모든 쿠폰 타입 응답 리스트
	 */
	@Override
	public List<CouponTypeResponse> getAllCouponTypes() {
		return couponTypeRepository.findAllBy().stream()
			.map(CouponTypeResponse::from)
			.toList();
	}

	/**
	 * 쿠폰 타입 이름으로 쿠폰 타입을 조회합니다.
	 *
	 * @param name 쿠폰 타입 이름
	 * @return 조회된 쿠폰 타입 엔티티
	 * @throws IllegalArgumentException 이름이 null 인 경우
	 * @throws CouponTypeNotFoundException 쿠폰 타입을 찾을 수 없는 경우
	 */
	@Override
	public CouponType getCouponType(String name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
		return couponTypeRepository.findAllByName(CouponScope.fromString(name))
			.orElseThrow(CouponTypeNotFoundException::new);
	}
}
