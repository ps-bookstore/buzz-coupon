package store.buzzbook.coupon.service.impl;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import store.buzzbook.coupon.common.constant.CouponScope;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.common.utils.DateFormatter;
import store.buzzbook.coupon.dto.coupon.CouponPoliciesResponse;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyConditionRequest;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.UpdateCouponPolicyRequest;
import store.buzzbook.coupon.entity.CategoryCoupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.repository.CategoryCouponRepository;
import store.buzzbook.coupon.repository.SpecificCouponRepository;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponTypeService;

@Service
@RequiredArgsConstructor
public class CouponPolicyServiceImpl implements CouponPolicyService {

	private final CouponPolicyRepository couponPolicyRepository;
	private final CategoryCouponRepository categoryCouponRepository;
	private final SpecificCouponRepository specificCouponRepository;
	private final CouponTypeService couponTypeService;

	@Override
	public Page<CouponPolicyResponse> getCouponPoliciesByPaging(CouponPolicyConditionRequest condition) {
		Page<CouponPolicy> couponPolicies = couponPolicyRepository.findAllByCondition(condition);
		return couponPolicies.map(CouponPolicyResponse::from);
	}

	@Override
	public CouponPoliciesResponse getCouponPoliciesByScope(List<String> scope) {
		Map<CouponScope, List<CouponPolicyResponse>> policiesMap = new EnumMap<>(CouponScope.class);
		policiesMap.put(CouponScope.GLOBAL, new ArrayList<>());
		policiesMap.put(CouponScope.BOOK, new ArrayList<>());
		policiesMap.put(CouponScope.CATEGORY, new ArrayList<>());

		for (String scopeName : scope) {
			CouponScope couponScope = CouponScope.fromString(scopeName);
			if (couponScope != null) {
				policiesMap.computeIfPresent(couponScope, (key, value) -> couponPolicyRepository
					.findAllByCouponTypeNameAndDeletedIsFalse(couponScope)
					.stream()
					.map(CouponPolicyResponse::from)
					.toList());
			}
		}

		return CouponPoliciesResponse.builder()
			.globalCouponPolicies(policiesMap.get(CouponScope.GLOBAL))
			.specificCouponPolicies(policiesMap.get(CouponScope.BOOK))
			.categoryCouponPolicies(policiesMap.get(CouponScope.CATEGORY))
			.build();
	}

	@Override
	public List<CouponPolicyResponse> getSpecificCoupons(int bookId) {
		validateId(bookId);

		return couponPolicyRepository.findAllByBookId(bookId).stream()
			.map(CouponPolicyResponse::from)
			.toList();
	}

	@Override
	public CouponPolicy getCouponPolicyById(int id) {
		validateId(id);
		return couponPolicyRepository.findById(id).orElseThrow(CouponPolicyNotFoundException::new);
	}

	@Transactional
	@Override
	public CreateCouponPolicyResponse createCouponPolicy(CreateCouponPolicyRequest request) {
		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponType couponType = couponTypeService.getCouponType(request.couponType());

		CouponPolicy couponPolicy = CouponPolicy.builder()
			.name(request.name())
			.discountType(DiscountType.fromString(request.discountType()))
			.discountRate(request.discountRate())
			.discountAmount(request.discountAmount())
			.standardPrice(request.standardPrice())
			.maxDiscountAmount(request.maxDiscountAmount())
			.period(request.period())
			.startDate(DateFormatter.toLocalDate(request.startDate()))
			.endDate(DateFormatter.toLocalDate(request.endDate()))
			.couponType(couponType)
			.deleted(false)
			.build();

		CouponPolicy savedPolicy = couponPolicyRepository.save(couponPolicy);

		if (couponType.getName().equals(CouponScope.BOOK)) {
			SpecificCoupon specificCoupon = SpecificCoupon.builder()
				.couponPolicy(savedPolicy)
				.bookId(request.targetId())
				.build();

			specificCouponRepository.save(specificCoupon);
		} else if (couponType.getName().equals(CouponScope.CATEGORY)) {
			CategoryCoupon categoryCoupon = CategoryCoupon.builder()
				.couponPolicy(savedPolicy)
				.categoryId(request.targetId())
				.build();

			categoryCouponRepository.save(categoryCoupon);
		}

		return CreateCouponPolicyResponse.from(savedPolicy);
	}

	@Override
	public CouponPolicyResponse updateCouponPolicy(int id, UpdateCouponPolicyRequest request) {
		validateId(id);

		if (Objects.isNull(request)) {
			throw new IllegalArgumentException("쿠폰 생성 요청을 찾을 수 없습니다.");
		}

		CouponPolicy couponPolicy = getCouponPolicyById(id);
		couponPolicy.changeEndDate(request.endDate());

		return CouponPolicyResponse.from(couponPolicy);
	}

	@Override
	public void deleteCouponPolicy(int id) {
		validateId(id);

		if (!couponPolicyRepository.existsById(id)) {
			throw new CouponPolicyNotFoundException();
		}

		CouponPolicy couponPolicy = getCouponPolicyById(id);
		couponPolicy.delete();
	}

	private void validateId(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("잘못된 파라미터 값입니다.");
		}
	}
}