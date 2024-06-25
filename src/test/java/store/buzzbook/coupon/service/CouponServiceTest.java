package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.dto.couponlog.CouponResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponResponse;
import store.buzzbook.coupon.dto.couponlog.UpdateCouponRequest;
import store.buzzbook.coupon.entity.Coupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.entity.constant.CouponStatus;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponRepository;
import store.buzzbook.coupon.service.impl.CouponServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
class CouponServiceTest {

	@Mock
	private CouponRepository couponRepository;

	@Mock
	private CouponPolicyService couponPolicyService;

	@InjectMocks
	private CouponServiceImpl couponLogService;

	private Coupon testCouponLog;
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

		testCouponLog = Coupon.builder()
			.id(1L)
			.couponPolicy(testCouponPolicy)
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(7))
			.status(CouponStatus.AVAILABLE)
			.build();
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		CreateCouponRequest testRequest = new CreateCouponRequest(
			1,
			ZonedDateTime.now(),
			ZonedDateTime.now().plusDays(5),
			CouponStatus.AVAILABLE
		);

		when(couponPolicyService.getCouponPolicyById(anyInt())).thenReturn(testCouponPolicy);
		when(couponRepository.save(any())).thenReturn(testCouponLog);

		// when
		CreateCouponResponse testResponse = couponLogService.createCoupon(testRequest);

		// then
		verify(couponPolicyService, times(1)).getCouponPolicyById(anyInt());
		verify(couponRepository, times(1)).save(any());
		assertEquals(testRequest.status(), testResponse.couponStatus());
		assertEquals(testRequest.couponPolicyId(), testResponse.couponPolicyResponse().id());
	}

	@Test
	@DisplayName("save IllegalArgumentException")
	void saveIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponLogService.createCoupon(null));
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		long id = 1L;
		UpdateCouponRequest testRequest = new UpdateCouponRequest(CouponStatus.USED);
		when(couponRepository.findById(id)).thenReturn(Optional.of(testCouponLog));

		// when
		CouponResponse testResponse = couponLogService.updateCoupon(id, testRequest);

		// then
		assertEquals(CouponStatus.USED, testResponse.status());
	}

	@Test
	@DisplayName("update IllegalArgumentException")
	void updateIllegalArgumentException() {
		// given
		UpdateCouponRequest testRequest = new UpdateCouponRequest(CouponStatus.USED);

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCoupon(-1, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCoupon(0, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCoupon(1, null));
	}
}
