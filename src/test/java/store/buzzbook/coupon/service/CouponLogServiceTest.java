package store.buzzbook.coupon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.util.Collections;
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

import store.buzzbook.coupon.dto.couponlog.CouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogRequest;
import store.buzzbook.coupon.dto.couponlog.CreateCouponLogResponse;
import store.buzzbook.coupon.dto.couponlog.UpdateCouponLogRequest;
import store.buzzbook.coupon.entity.CouponLog;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponStatus;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponLogRepository;
import store.buzzbook.coupon.service.impl.CouponLogServiceImpl;

@ExtendWith(MockitoExtension.class)
@Transactional
class CouponLogServiceTest {

	@Mock
	private CouponLogRepository couponLogRepository;

	@Mock
	private CouponPolicyService couponPolicyService;

	@InjectMocks
	private CouponLogServiceImpl couponLogService;

	private Pageable testPageable;
	private Page<CouponLogResponse> testCouponLogPage;
	private CouponLog testCouponLog;
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

		testCouponLog = CouponLog.builder()
			.id(1L)
			.couponPolicy(testCouponPolicy)
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(7))
			.status(CouponStatus.AVAILABLE)
			.userId(1)
			.build();

		testPageable = PageRequest.of(0, 10);
		testCouponLogPage = new PageImpl<>(Collections.singletonList(CouponLogResponse.from(testCouponLog)));
	}

	@Test
	@DisplayName("get coupon log by paging")
	void getCouponLogByPaging() {
		// given
		when(couponLogRepository.findAllByUserId(anyLong(), any(Pageable.class))).thenReturn(testCouponLogPage);
		long userId = 1L;

		// when
		Page<CouponLogResponse> result = couponLogService.getCouponLogByPaging(userId, testPageable);

		// then
		assertEquals(1, result.getTotalElements());
		assertEquals(1, result.getContent().size());
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		CreateCouponLogRequest testRequest = new CreateCouponLogRequest(
			1,
			ZonedDateTime.now(),
			ZonedDateTime.now().plusDays(5),
			CouponStatus.AVAILABLE,
			1L
		);

		when(couponPolicyService.getCouponPolicyById(anyInt())).thenReturn(testCouponPolicy);
		when(couponLogRepository.save(any())).thenReturn(testCouponLog);

		// when
		CreateCouponLogResponse testResponse = couponLogService.createCouponLog(testRequest);

		// then
		verify(couponPolicyService, times(1)).getCouponPolicyById(anyInt());
		verify(couponLogRepository, times(1)).save(any());
		assertEquals(testRequest.userId(), testResponse.userId());
	}

	@Test
	@DisplayName("save IllegalArgumentException")
	void saveIllegalArgumentException() {
		// given

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponLogService.createCouponLog(null));
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		long id = 1L;
		UpdateCouponLogRequest testRequest = new UpdateCouponLogRequest(CouponStatus.USED);
		when(couponLogRepository.findById(id)).thenReturn(Optional.of(testCouponLog));
		when(couponLogRepository.save(any())).thenReturn(testCouponLog);

		// when
		CouponLogResponse testResponse = couponLogService.updateCouponLog(id, testRequest);

		// then
		verify(couponLogRepository, times(1)).save(any());
		assertEquals(CouponStatus.USED, testResponse.status());
	}

	@Test
	@DisplayName("update IllegalArgumentException")
	void updateIllegalArgumentException() {
		// given
		UpdateCouponLogRequest testRequest = new UpdateCouponLogRequest(CouponStatus.USED);

		// when & then
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCouponLog(-1, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCouponLog(0, testRequest));
		assertThrows(IllegalArgumentException.class, () -> couponLogService.updateCouponLog(1, null));
	}
}
