package com.nhnacademy.couponapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.nhnacademy.couponapi.entity.Coupon;
import com.nhnacademy.couponapi.entity.CouponPolicy;
import com.nhnacademy.couponapi.entity.SpecificCoupon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class SpecificCouponRepositoryTest {

	@Autowired
	private SpecificCouponRepository specificCouponRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	private Coupon testCoupon;
	private CouponPolicy testCouponPolicy;
	private SpecificCoupon testSpecificCoupon;

	@BeforeEach
	public void setUp() {
		testCoupon = Coupon.builder()
			.name("test")
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.isUsed(false)
			.build();

		testCouponPolicy = CouponPolicy.builder()
			.name("testGlobalPolicy")
			.discountAmount(BigDecimal.valueOf(3000.00))
			.standardPrice(BigDecimal.valueOf(10000.00))
			.maxDiscountAmount(BigDecimal.valueOf(15000.00))
			.build();

		testSpecificCoupon = SpecificCoupon.builder()
			.coupon(testCoupon)
			.couponPolicy(testCouponPolicy)
			.bookId(1L)
			.build();

		couponRepository.save(testCoupon);
		couponPolicyRepository.save(testCouponPolicy);
		specificCouponRepository.save(testSpecificCoupon);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		Coupon newCoupon = Coupon.builder()
			.name("new")
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.isUsed(false)
			.build();

		SpecificCoupon newSpecificCoupon = SpecificCoupon.builder()
			.coupon(newCoupon)
			.couponPolicy(testCouponPolicy)
			.bookId(1L)
			.build();

		// when
		couponRepository.save(newCoupon);
		specificCouponRepository.save(newSpecificCoupon);
		Optional<SpecificCoupon> optionalSpecificCoupon = specificCouponRepository.findById(newSpecificCoupon.getId());

		// then
		assertTrue(optionalSpecificCoupon.isPresent());
		assertEquals(newSpecificCoupon, optionalSpecificCoupon.get());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		SpecificCoupon savedSpecificCoupon = specificCouponRepository.findById(testSpecificCoupon.getId()).orElse(null);

		// when
		assert savedSpecificCoupon != null;
		specificCouponRepository.delete(savedSpecificCoupon);
		Optional<SpecificCoupon> optionalSpecificCoupon = specificCouponRepository.findById(testSpecificCoupon.getId());

		// then
		assertFalse(optionalSpecificCoupon.isPresent());
	}

	@Test
	@DisplayName("exist by coupon id")
	void existByCouponId() {
		// given

		// when
		boolean exists = specificCouponRepository.existsByCouponId(testCoupon.getId());
		boolean notExists = specificCouponRepository.existsByCouponId(2L);

		// then
		assertTrue(exists);
		assertFalse(notExists);
	}

	@Test
	@DisplayName("exist by book id")
	void existByBookId() {
		// given

		// when
		boolean exists = specificCouponRepository.existsByBookId(1L);
		boolean notExists = specificCouponRepository.existsByBookId(2L);

		// then
		assertTrue(exists);
		assertFalse(notExists);
	}
}
