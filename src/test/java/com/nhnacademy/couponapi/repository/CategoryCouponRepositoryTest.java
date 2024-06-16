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

import com.nhnacademy.couponapi.entity.CategoryCoupon;
import com.nhnacademy.couponapi.entity.Coupon;
import com.nhnacademy.couponapi.entity.CouponPolicy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CategoryCouponRepositoryTest {

	@Autowired
	private CategoryCouponRepository categoryCouponRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	private Coupon testCoupon;
	private CouponPolicy testCouponPolicy;
	private CategoryCoupon testCategoryCoupon;

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

		testCategoryCoupon = CategoryCoupon.builder()
			.coupon(testCoupon)
			.couponPolicy(testCouponPolicy)
			.categoryId(1L)
			.build();

		couponRepository.save(testCoupon);
		couponPolicyRepository.save(testCouponPolicy);
		categoryCouponRepository.save(testCategoryCoupon);
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

		CategoryCoupon newCategoryCoupon = CategoryCoupon.builder()
			.coupon(newCoupon)
			.couponPolicy(testCouponPolicy)
			.build();

		// when
		couponRepository.save(newCoupon);
		categoryCouponRepository.save(newCategoryCoupon);
		Optional<CategoryCoupon> optionalCategoryCoupon = categoryCouponRepository.findById(newCategoryCoupon.getId());

		//then
		assertTrue(optionalCategoryCoupon.isPresent());
		assertEquals(newCategoryCoupon, optionalCategoryCoupon.get());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		CategoryCoupon savedCategoryCoupon = categoryCouponRepository.findById(testCategoryCoupon.getId()).orElse(null);

		// when
		assert savedCategoryCoupon != null;
		categoryCouponRepository.delete(savedCategoryCoupon);
		Optional<CategoryCoupon> optionalCategoryCoupon = categoryCouponRepository.findById(testCategoryCoupon.getId());

		// then
		assertFalse(optionalCategoryCoupon.isPresent());
	}

	@Test
	@DisplayName("exist by coupon id")
	void existByCouponId() {
		// given

		// when
		boolean exists = categoryCouponRepository.existsByCouponId(testCoupon.getId());
		boolean notExists = categoryCouponRepository.existsByCouponId(2L);

		// then
		assertTrue(exists);
		assertFalse(notExists);
	}

	@Test
	@DisplayName("exist by category id")
	void existByCategoryId() {
		// given

		// when
		boolean exists = categoryCouponRepository.existsByCategoryId(testCategoryCoupon.getCategoryId());
		boolean notExists = categoryCouponRepository.existsByCategoryId(2L);

		// then
		assertTrue(exists);
		assertFalse(notExists);
	}
}
