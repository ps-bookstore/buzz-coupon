package store.buzzbook.coupon.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.entity.CouponLog;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.entity.constant.CouponStatus;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CouponLogRepositoryTest {

	@Autowired
	private CouponLogRepository couponLogRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponLog testCouponLog;
	private CouponPolicy testCouponPolicy;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
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
			.couponPolicy(testCouponPolicy)
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.userId(1L)
			.build();

		couponTypeRepository.save(testCouponType);
		couponPolicyRepository.save(testCouponPolicy);
		couponLogRepository.save(testCouponLog);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		CouponLog newCouponLog = CouponLog.builder()
			.couponPolicy(testCouponPolicy)
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.userId(1L)
			.build();

		// when
		couponLogRepository.save(newCouponLog);
		Optional<CouponLog> foundCoupon = couponLogRepository.findById(newCouponLog.getId());

		// then
		assertThat(foundCoupon).isPresent();
		assertThat(foundCoupon.get().getId()).isEqualTo(newCouponLog.getId());
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		CouponLog foundCouponLog = couponLogRepository.findById(testCouponLog.getId()).orElse(null);
		CouponStatus updatedStatus = CouponStatus.USED;

		// when
		assert foundCouponLog != null;
		foundCouponLog.setStatus(updatedStatus);
		couponLogRepository.save(foundCouponLog);

		// then
		assertEquals(foundCouponLog.getStatus(), updatedStatus);
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		CouponLog savedCouponLog = couponLogRepository.findById(testCouponLog.getId()).orElse(null);

		// when
		assert savedCouponLog != null;
		couponLogRepository.deleteById(savedCouponLog.getId());
		Optional<CouponLog> foundCoupon = couponLogRepository.findById(savedCouponLog.getId());

		// then
		assertThat(foundCoupon).isNotPresent();
	}

	@Test
	@DisplayName("find all")
	void findAll() {
		// given
		CouponLog newCouponLog = CouponLog.builder()
			.couponPolicy(testCouponPolicy)
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.userId(1L)
			.build();

		couponLogRepository.save(newCouponLog);

		// when
		Iterable<CouponLog> coupons = couponLogRepository.findAll();

		// then
		assertThat(coupons).hasSize(2);
	}
}
