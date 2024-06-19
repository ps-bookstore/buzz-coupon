package store.buzzbook.coupon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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
		return couponTypeRepository.findAllBy();
	}

	@Override
	public CouponType getCouponType(String name) {
		return couponTypeRepository.findByName(name)
			.orElseThrow(CouponTypeNotFoundException::new);
	}
}
