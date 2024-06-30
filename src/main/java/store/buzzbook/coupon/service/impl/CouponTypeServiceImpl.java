package store.buzzbook.coupon.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.service.CouponTypeService;

@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {

	private final CouponTypeRepository couponTypeRepository;

	@Override
	public List<CouponTypeResponse> getAllCouponTypes() {
		return couponTypeRepository.findAllBy().stream()
			.map(CouponTypeResponse::from)
			.toList();
	}

	@Override
	public CouponType getCouponType(String name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
		return couponTypeRepository.findAllByName(CouponRange.fromString(name))
			.orElseThrow(CouponTypeNotFoundException::new);
	}
}
