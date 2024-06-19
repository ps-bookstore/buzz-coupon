package store.buzzbook.coupon.service.impl;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogResponse;
import store.buzzbook.coupon.entity.CouponLog;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.repository.CouponLogRepository;
import store.buzzbook.coupon.service.CouponLogService;
import store.buzzbook.coupon.service.CouponPolicyService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponLogServiceImpl implements CouponLogService {

	private final CouponLogRepository couponLogRepository;
	private final CouponPolicyService couponPolicyService;

	@Override
	public Page<CouponLogResponse> getCouponLogByPaging(long userId, Pageable pageable) {
		validateId(userId);
		return couponLogRepository.findAllByUserId(userId, pageable);
	}

	@Override
	public CreateCouponLogResponse createCouponLog(CreateCouponLogRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 로그 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = couponPolicyService.getCouponPolicyById(request.couponPolicyId());

		CouponLog couponLog = CouponLog.builder()
			.createDate(request.createDate())
			.expireDate(request.expireDate())
			.status(request.status())
			.userId(request.userId())
			.couponPolicy(couponPolicy)
			.build();

		return CreateCouponLogResponse.from(couponLogRepository.save(couponLog));
	}

	private void validateId(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 요청입니다.");
		}
	}
}
