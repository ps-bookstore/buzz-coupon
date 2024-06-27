package store.buzzbook.coupon.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import store.buzzbook.coupon.entity.Coupon;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.common.constant.CouponStatus;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponRepositoryTest {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private Coupon testCouponLog;
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
			.startDate(LocalDate.now())
			.endDate(LocalDate.now().plusDays(1))
			.name("test")
			.maxDiscountAmount(10000)
			.build();

		testCouponLog = Coupon.builder()
			.couponPolicy(testCouponPolicy)
			.createDate(LocalDate.now())
			.expireDate(LocalDate.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.build();

		couponTypeRepository.save(testCouponType);
		couponPolicyRepository.save(testCouponPolicy);
		couponRepository.save(testCouponLog);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		Coupon newCouponLog = Coupon.builder()
			.couponPolicy(testCouponPolicy)
			.createDate(LocalDate.now())
			.expireDate(LocalDate.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.build();

		// when
		couponRepository.save(newCouponLog);
		Optional<Coupon> foundCoupon = couponRepository.findById(newCouponLog.getId());

		// then
		assertThat(foundCoupon).isPresent();
		assertThat(foundCoupon.get().getId()).isEqualTo(newCouponLog.getId());
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		Coupon foundCoupon = couponRepository.findById(testCouponLog.getId()).orElse(null);
		CouponStatus updatedStatus = CouponStatus.USED;

		// when
		assert foundCoupon != null;
		foundCoupon.changeStatus(updatedStatus);
		couponRepository.save(foundCoupon);

		// then
		assertEquals(foundCoupon.getStatus(), updatedStatus);
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		Coupon savedCouponLog = couponRepository.findById(testCouponLog.getId()).orElse(null);

		// when
		assert savedCouponLog != null;
		couponRepository.deleteById(savedCouponLog.getId());
		Optional<Coupon> foundCoupon = couponRepository.findById(savedCouponLog.getId());

		// then
		assertThat(foundCoupon).isNotPresent();
	}

	@Test
	@DisplayName("find all")
	void findAll() {
		// given
		Coupon newCouponLog = Coupon.builder()
			.couponPolicy(testCouponPolicy)
			.createDate(LocalDate.now())
			.expireDate(LocalDate.now().plusDays(2))
			.status(CouponStatus.AVAILABLE)
			.build();

		couponRepository.save(newCouponLog);

		// when
		Iterable<Coupon> coupons = couponRepository.findAll();

		// then
		assertThat(coupons).hasSize(2);
	}
}
