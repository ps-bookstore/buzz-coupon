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
import store.buzzbook.coupon.dto.coupontype.CreateCouponTypeRequest;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.service.impl.CouponTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
class CouponTypeServiceTest {

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
			.name(CouponRange.BOOK)
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
		when(couponTypeRepository.findAllByName(any())).thenReturn(Optional.of(testCouponType));

		// when
		CouponType newCouponType = couponTypeService.getCouponType(testCouponType.getName().toString());

		// then
		assertEquals(testCouponType, newCouponType);
	}

	@Test
	@DisplayName("get coupon type with IllegalArgumentException")
	void getCouponTypeWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponTypeService.getCouponType(null));
	}

	@Test
	@DisplayName("get coupon type with coupon type not found exception")
	void getCouponTypeNotFound() {
		// given
		when(couponTypeRepository.findAllByName(any())).thenThrow(CouponTypeNotFoundException.class);

		// when & then
		assertThrows(CouponTypeNotFoundException.class, () -> couponTypeService.getCouponType("global"));
	}

	@Test
	@DisplayName("create")
	void create() {
		// given
		CreateCouponTypeRequest testRequest = new CreateCouponTypeRequest(CouponRange.BOOK);
		when(couponTypeRepository.save(any())).thenReturn(testCouponType);

		// when
		CouponTypeResponse testResponse = couponTypeService.createCouponType(testRequest);

		// then
		assertEquals(testRequest.name(), testResponse.name());
	}

	@Test
	@DisplayName("create with IllegalArgumentException")
	void createWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponTypeService.createCouponType(null));
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		doNothing().when(couponTypeRepository).deleteById(anyInt());

		// when
		couponTypeService.deleteCouponType(1);

		// then
		verify(couponTypeRepository, times(1)).deleteById(anyInt());
	}

	@Test
	@DisplayName("delete with IllegalArgumentException")
	void deleteWithIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponTypeService.deleteCouponType(-1));
		assertThrows(IllegalArgumentException.class, () -> couponTypeService.deleteCouponType(0));
	}
}
