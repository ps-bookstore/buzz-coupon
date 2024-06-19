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

import store.buzzbook.coupon.common.exception.CategoryCouponNotFoundException;
import store.buzzbook.coupon.dto.categorycoupon.CreateCategoryCouponRequest;
import store.buzzbook.coupon.entity.CategoryCoupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CategoryCouponRepository;
import store.buzzbook.coupon.service.impl.CategoryCouponServiceImpl;

@ExtendWith(MockitoExtension.class)
class CategoryCouponServiceTest {

	@Mock
	private CategoryCouponRepository categoryCouponRepository;

	@Mock
	private CouponPolicyService couponPolicyService;

	@InjectMocks
	private CategoryCouponServiceImpl categoryCouponService;

	private CouponPolicy testCouponPolicy;
	private CategoryCoupon testCategoryCoupon;

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

		testCategoryCoupon = CategoryCoupon.builder()
			.categoryId(1)
			.couponPolicy(testCouponPolicy)
			.build();
	}

	@Test
	@DisplayName("create")
	void create() {
		// given
		CreateCategoryCouponRequest testRequest = new CreateCategoryCouponRequest(testCategoryCoupon.getId(), 1);
		when(couponPolicyService.getCouponPolicyById(anyInt())).thenReturn(testCouponPolicy);
		when(categoryCouponRepository.save(any())).thenReturn(testCategoryCoupon);

		// when
		categoryCouponService.createCategoryCoupon(testRequest);

		// then
		verify(couponPolicyService, times(1)).getCouponPolicyById(anyInt());
		verify(categoryCouponRepository, times(1)).save(any());
	}

	@Test
	@DisplayName("create with IllegalArgumentException")
	void createWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> categoryCouponService.createCategoryCoupon(null));
	}

	@Test
	@DisplayName("delete by coupon policy id")
	void deleteByCouponPolicyId() {
		// given
		when(categoryCouponRepository.existsByCouponPolicyId(anyInt())).thenReturn(true);
		doNothing().when(categoryCouponRepository).deleteByCouponPolicyId(anyInt());

		// when
		categoryCouponService.deleteCategoryCouponByCouponPolicyId(testCouponPolicy.getId());

		// then
		verify(categoryCouponRepository, times(1)).existsByCouponPolicyId(anyInt());
		verify(categoryCouponRepository, times(1)).deleteByCouponPolicyId(anyInt());
	}

	@Test
	@DisplayName("delete by coupon policy id with IllegalArgumentException")
	void deleteByCouponPolicyIdWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class,
			() -> categoryCouponService.deleteCategoryCouponByCouponPolicyId(-1));
		assertThrows(IllegalArgumentException.class,
			() -> categoryCouponService.deleteCategoryCouponByCouponPolicyId(0));
	}

	@Test
	@DisplayName("delete by coupon policy id with CategoryCouponNotFoundException")
	void deleteByCouponPolicyIdWithCategoryCouponNotFoundException() {
		// given
		when(categoryCouponRepository.existsByCouponPolicyId(anyInt())).thenReturn(false);

		// when & then
		assertThrows(CategoryCouponNotFoundException.class,
			() -> categoryCouponService.deleteCategoryCouponByCouponPolicyId(testCouponPolicy.getId()));
	}

	@Test
	@DisplayName("delete by category id")
	void deleteByCategoryId() {
		// given
		when(categoryCouponRepository.existsByCategoryId(anyInt())).thenReturn(true);
		doNothing().when(categoryCouponRepository).deleteByCategoryId(anyInt());

		// when
		categoryCouponService.deleteCategoryCouponByCategoryId(1);

		// then
		verify(categoryCouponRepository, times(1)).existsByCategoryId(anyInt());
		verify(categoryCouponRepository, times(1)).deleteByCategoryId(anyInt());
	}

	@Test
	@DisplayName("delete by category id with IllegalArgumentException")
	void deleteByCategoryIdWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> categoryCouponService.deleteCategoryCouponByCategoryId(-1));
		assertThrows(IllegalArgumentException.class, () -> categoryCouponService.deleteCategoryCouponByCategoryId(0));
	}

	@Test
	@DisplayName("delete by category id with CategoryCouponNotFoundException")
	void deleteByCategoryIdWithCategoryCouponNotFoundException() {
		// given
		when(categoryCouponRepository.existsByCategoryId(anyInt())).thenReturn(false);

		// when & then
		assertThrows(CategoryCouponNotFoundException.class,
			() -> categoryCouponService.deleteCategoryCouponByCategoryId(1));
	}
}
