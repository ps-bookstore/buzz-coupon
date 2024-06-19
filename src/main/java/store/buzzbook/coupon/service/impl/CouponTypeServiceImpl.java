package store.buzzbook.coupon.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.dto.coupontype.CreateCouponTypeRequest;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.service.CouponTypeService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {

	private final CouponTypeRepository couponTypeRepository;

	@Override
	public List<CouponTypeResponse> getAllCouponTypes() {
		return couponTypeRepository.findAllBy();
	}

	@Override
	public CouponType getCouponType(String name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
		return couponTypeRepository.findByName(name)
			.orElseThrow(CouponTypeNotFoundException::new);
	}

	@Override
	public CouponTypeResponse createCouponType(CreateCouponTypeRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 타입 생성 요청을 찾을 수 없습니다.");
		}

		return CouponTypeResponse.from(couponTypeRepository.save(request.toEntity()));
	}

	@Override
	public void deleteCouponType(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}

		couponTypeRepository.deleteById(id);
	}
}
