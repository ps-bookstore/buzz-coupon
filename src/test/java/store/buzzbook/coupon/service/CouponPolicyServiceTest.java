package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import store.buzzbook.coupon.common.exception.CouponPolicyNotFoundException;
import store.buzzbook.coupon.dto.couponpolicy.CouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyRequest;
import store.buzzbook.coupon.dto.couponpolicy.CreateCouponPolicyResponse;
import store.buzzbook.coupon.dto.couponpolicy.UpdateCouponPolicyRequest;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.SpecificCouponRepository;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyQuerydslRepository;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;
import store.buzzbook.coupon.service.impl.CouponPolicyServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
class CouponPolicyServiceTest {

	@Mock
	private CouponPolicyRepository couponPolicyRepository;

	@Mock
	private CouponTypeService couponTypeService;

	@Mock
	private SpecificCouponRepository specificCouponRepository;

	@Mock
	private CouponPolicyQuerydslRepository couponPolicyQuerydslRepository;

	@InjectMocks
	private CouponPolicyServiceImpl couponPolicyService;

	private Pageable pageable;
	private Page<CouponPolicyResponse> couponPolicyPage;
	private CouponPolicy testCouponPolicy;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
			.build();

		testCouponPolicy = CouponPolicy.builder()
			.id(1)
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
	@DisplayName("get specific coupons by bookId")
	void getSpecificCoupons() {
		// given
		when(couponPolicyQuerydslRepository.findAllByBookId(anyInt())).thenReturn(List.of(testCouponPolicy));

		// when
		List<CouponPolicyResponse> result = couponPolicyService.getSpecificCoupons(1);

		// then
		assertEquals(1, result.size());
		assertEquals(testCouponPolicy.getId(), result.get(0).id());
		verify(couponPolicyQuerydslRepository, times(1)).findAllByBookId(anyInt());
	}

	@Test
	@DisplayName("create")
	void create() {
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
			"book",
			false,
			1
		);

		SpecificCoupon testSpecificCoupon = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy)
			.bookId(testRequest.targetId())
			.build();

		when(couponTypeService.getCouponType(anyString())).thenReturn(
			CouponType.builder().name(CouponRange.BOOK).build());
		when(couponPolicyRepository.save(any(CouponPolicy.class))).thenReturn(testCouponPolicy);
		when(specificCouponRepository.save(any(SpecificCoupon.class))).thenReturn(testSpecificCoupon);

		// when
		CreateCouponPolicyResponse result = couponPolicyService.createCouponPolicy(testRequest);

		// then
		assertEquals(testRequest.name(), result.name());
	}

	@Test
	@DisplayName("create IllegalArgumentException")
	void createIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.createCouponPolicy(null));
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		UpdateCouponPolicyRequest testRequest = new UpdateCouponPolicyRequest(ZonedDateTime.now().plusDays(10));
		when(couponPolicyRepository.findById(anyInt())).thenReturn(Optional.of(testCouponPolicy));
		when(couponPolicyRepository.save(any(CouponPolicy.class))).thenReturn(testCouponPolicy);

		// when
		couponPolicyService.updateCouponPolicy(1, testRequest);

		// then
		verify(couponPolicyRepository, times(1)).findById(anyInt());
		verify(couponPolicyRepository, times(1)).save(any(CouponPolicy.class));
	}

	@Test
	@DisplayName("update IllegalArgumentException")
	void updateIllegalArgumentException() {
		// given
		UpdateCouponPolicyRequest testRequest = new UpdateCouponPolicyRequest(ZonedDateTime.now().plusDays(10));

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.updateCouponPolicy(0, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.updateCouponPolicy(-1, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.updateCouponPolicy(1, null));
	}

	@Test
	@DisplayName("update coupon policy not found exception")
	void updateCouponPolicyNotFoundException() {
		// given
		UpdateCouponPolicyRequest testRequest = new UpdateCouponPolicyRequest(ZonedDateTime.now().plusDays(10));
		when(couponPolicyRepository.findById(anyInt())).thenReturn(Optional.empty());

		// when & then
		assertThrows(CouponPolicyNotFoundException.class, () -> couponPolicyService.updateCouponPolicy(1, testRequest));
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		when(couponPolicyRepository.existsById(anyInt())).thenReturn(true);
		when(couponPolicyRepository.findById(anyInt())).thenReturn(Optional.of(testCouponPolicy));

		// when
		couponPolicyService.deleteCouponPolicy(1);

		// then
		verify(couponPolicyRepository, times(1)).save(any());
	}

	@Test
	@DisplayName("delete with IllegalArgumentException")
	void deleteIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.deleteCouponPolicy(-1));
		assertThrows(IllegalArgumentException.class, () -> couponPolicyService.deleteCouponPolicy(0));
	}

	@Test
	@DisplayName("delete with CouponPolicyNotFoundException")
	void deleteCouponPolicyNotFoundException() {
		// given
		when(couponPolicyRepository.existsById(anyInt())).thenReturn(false);

		// when & then
		assertThrows(CouponPolicyNotFoundException.class, () -> couponPolicyService.deleteCouponPolicy(1));
	}
}

