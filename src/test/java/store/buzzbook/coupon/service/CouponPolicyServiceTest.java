package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponPolicyRepository;
import store.buzzbook.coupon.service.impl.CouponPolicyServiceImpl;

@ExtendWith(MockitoExtension.class)
class CouponPolicyServiceTest {

	@Mock
	private CouponPolicyRepository couponPolicyRepository;

	@InjectMocks
	private CouponPolicyServiceImpl couponPolicyService;

	private Pageable pageable;
	private Page<CouponPolicyResponse> couponPolicyPage;
	private CouponPolicyResponse testCouponPolicyResponse;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
			.build();

		CouponPolicy testCouponPolicy = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountType(DiscountType.AMOUNT)
			.discountAmount(10000)
			.discountRate(1.0)
			.startDate(ZonedDateTime.now())
			.endDate(ZonedDateTime.now().plusDays(1))
			.name("test")
			.maxDiscountAmount(10000)
			.build();

		pageable = PageRequest.of(0, 10);
		couponPolicyPage = new PageImpl<>(Collections.singletonList(CouponPolicyResponse.from(testCouponPolicy)));
	}

	@Test
	@DisplayName("get coupon policies by paging")
	void testGetCouponPoliciesByPaging() {
		// given
		when(couponPolicyRepository.findAllBy(any(Pageable.class))).thenReturn(couponPolicyPage);

		// when
		Page<CouponPolicyResponse> result = couponPolicyService.getCouponPoliciesByPaging(pageable);

		// then
		assertEquals(1, result.getTotalElements());
		assertEquals(1, result.getContent().size());
	}
}

