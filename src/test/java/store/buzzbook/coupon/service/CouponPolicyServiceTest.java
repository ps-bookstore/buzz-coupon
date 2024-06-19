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
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponPolicyRepository;
import store.buzzbook.coupon.service.impl.CouponPolicyServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
class CouponPolicyServiceTest {

	@Mock
	private CouponPolicyRepository couponPolicyRepository;

	@Mock
	private CouponTypeService couponTypeService;

	@InjectMocks
	private CouponPolicyServiceImpl couponPolicyService;

	private Pageable pageable;
	private Page<CouponPolicyResponse> couponPolicyPage;
	private CouponPolicy testCouponPolicy;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name("book")
			.build();

		testCouponPolicy = CouponPolicy.builder()
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
	void getCouponPoliciesByPaging() {
		// given
		when(couponPolicyRepository.findAllBy(any(Pageable.class))).thenReturn(couponPolicyPage);

		// when
		Page<CouponPolicyResponse> result = couponPolicyService.getCouponPoliciesByPaging(pageable);

		// then
		assertEquals(1, result.getTotalElements());
		assertEquals(1, result.getContent().size());
	}

	@Test
	@DisplayName("create coupon policy")
	void createCouponPolicy() {
		// given
		CreateCouponPolicyRequest testRequest = new CreateCouponPolicyRequest(
			"test",
			"rate",
			1.0,
			3000,
			10000,
			10000,
			14,
			ZonedDateTime.now(),
			ZonedDateTime.now().plusDays(10),
			"global"
		);

		when(couponTypeService.getCouponType(anyString())).thenReturn(CouponType.builder().name("book").build());

		// when
		CreateCouponPolicyResponse result = couponPolicyService.createCouponPolicy(testRequest);

		// then
		assertEquals(testRequest.name(), result.name());
	}

	@Test
	@DisplayName("create coupon policy IllegalArgumentException")
	void createCouponPolicyIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.createCouponPolicy(null));
	}

	@Test
	@DisplayName("delete coupon policy")
	void deleteCouponPolicy() {
		// given
		doNothing().when(couponPolicyRepository).deleteById(anyInt());

		// when
		couponPolicyService.deleteCouponPolicy(1);

		// then
		verify(couponPolicyRepository, times(1)).deleteById(anyInt());
	}

	@Test
	@DisplayName("delete coupon policy IllegalArgumentException")
	void deleteCouponPolicyIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.deleteCouponPolicy(-1));
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.deleteCouponPolicy(0));
	}
}

