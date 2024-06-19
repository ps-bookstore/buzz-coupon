package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponRequest;
import store.buzzbook.coupon.dto.specificcoupon.CreateSpecificCouponResponse;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.SpecificCouponRepository;
import store.buzzbook.coupon.service.impl.SpecificCouponServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SpecificCouponServiceTest {

	@Mock
	private SpecificCouponRepository specificCouponRepository;

	@Mock
	private CouponPolicyService couponPolicyService;

	@InjectMocks
	private SpecificCouponServiceImpl specificCouponService;

	private CouponPolicy testCouponPolicy;
	private SpecificCoupon testSpecificCoupon;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name("book")
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

		testSpecificCoupon = SpecificCoupon.builder()
			.id(1)
			.couponPolicy(testCouponPolicy)
			.bookId(1)
			.build();
	}

	@Test
	@DisplayName("create")
	void create() {
		// given
		CreateSpecificCouponRequest testRequest = new CreateSpecificCouponRequest(
			testCouponPolicy.getId(),
			1
		);
		when(couponPolicyService.getCouponPolicyById(anyInt())).thenReturn(testCouponPolicy);
		when(specificCouponRepository.save(any())).thenReturn(testSpecificCoupon);

		// when
		CreateSpecificCouponResponse testResponse = specificCouponService.createSpecificCoupon(testRequest);

		// then
		verify(couponPolicyService, times(1)).getCouponPolicyById(anyInt());
		verify(specificCouponRepository, times(1)).save(any());
		assertEquals(testRequest.couponPolicyId(), testResponse.couponPolicyResponse().id());
		assertEquals(testRequest.bookId(), testResponse.bookId());
	}

	@Test
	@DisplayName("create with IllegalArgumentException")
	void createWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> specificCouponService.createSpecificCoupon(null));
	}
}
