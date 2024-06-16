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
import com.nhnacademy.couponapi.entity.GlobalCoupon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class GlobalCouponRepositoryTest {

	@Autowired
	private GlobalCouponRepository globalCouponRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	private Coupon testCoupon;
	private CouponPolicy testCouponPolicy;
	private GlobalCoupon testGlobalCoupon;

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

		testGlobalCoupon = GlobalCoupon.builder()
			.coupon(testCoupon)
			.couponPolicy(testCouponPolicy)
			.build();

		couponRepository.save(testCoupon);
		couponPolicyRepository.save(testCouponPolicy);
		globalCouponRepository.save(testGlobalCoupon);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		GlobalCoupon newGlobalCoupon = GlobalCoupon.builder()
			.coupon(testCoupon)
			.couponPolicy(testCouponPolicy)
			.build();

		globalCouponRepository.save(newGlobalCoupon);

		// when
		Optional<GlobalCoupon> savedGlobalCoupon = globalCouponRepository.findById(testGlobalCoupon.getId());

		// then
		assertTrue(savedGlobalCoupon.isPresent());
		assertEquals(newGlobalCoupon.getCoupon(), savedGlobalCoupon.get().getCoupon());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		GlobalCoupon savedGlobalCoupon = globalCouponRepository.findById(testGlobalCoupon.getId()).orElse(null);

		// when
		assert savedGlobalCoupon != null;
		globalCouponRepository.delete(savedGlobalCoupon);
		Optional<GlobalCoupon> deletedGlobalCoupon = globalCouponRepository.findById(testGlobalCoupon.getId());

		// then
		assertFalse(deletedGlobalCoupon.isPresent());
	}

	@Test
	@DisplayName("exist by coupon id")
	void existByCouponId() {
		// given

		// when
		boolean exist = globalCouponRepository.existsByCouponId(testCoupon.getId());
		boolean notExist = globalCouponRepository.existsByCouponId(2L);

		// then
		assertTrue(exist);
		assertFalse(notExist);
	}
}
