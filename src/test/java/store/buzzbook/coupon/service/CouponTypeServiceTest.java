package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.common.exception.CouponTypeNotFoundException;
import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.service.impl.CouponTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
public class CouponTypeServiceTest {

	@Mock
	private CouponTypeRepository couponTypeRepository;

	@InjectMocks
	private CouponTypeServiceImpl couponTypeService;

	private CouponType testCouponType;
	private CouponTypeResponse testCouponTypeResponse;

	@BeforeEach
	void setUp() {
		testCouponType = CouponType.builder()
			.id(1)
			.name("test")
			.build();

		testCouponTypeResponse = CouponTypeResponse.from(testCouponType);
	}

	@Test
	@DisplayName("get all coupon type")
	void getAllCouponType() {
		// given
		when(couponTypeRepository.findAllBy()).thenReturn(List.of(testCouponTypeResponse));

		// when
		List<CouponTypeResponse> testList = couponTypeService.getAllCouponTypes();

		// then
		assertEquals(1, testList.size());
	}

	@Test
	@DisplayName("get coupon type")
	void getCouponType() {
		// given
		when(couponTypeRepository.findByName(anyString())).thenReturn(Optional.of(testCouponType));

		// when
		CouponType newCouponType = couponTypeService.getCouponType(testCouponType.getName());

		// then
		assertEquals(testCouponType, newCouponType);
	}

	@Test
	@DisplayName("get coupon type with coupon type not found exception")
	void getCouponTypeNotFound() {
		// given

		// when & then
		assertThrows(CouponTypeNotFoundException.class, () -> couponTypeService.getCouponType(null));
	}
}
