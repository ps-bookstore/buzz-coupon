package com.nhnacademy.couponapi.repository;

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

import com.nhnacademy.couponapi.entity.Coupon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CouponRepositoryTest {

	@Autowired
	private CouponRepository couponRepository;

	private Coupon testCoupon;

	@BeforeEach
	void setUp() {
		testCoupon = Coupon.builder()
			.name("test")
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.isUsed(false)
			.build();

		couponRepository.save(testCoupon);
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

		// when
		couponRepository.save(newCoupon);
		Optional<Coupon> foundCoupon = couponRepository.findById(newCoupon.getId());

		// then
		assertThat(foundCoupon).isPresent();
		assertThat(foundCoupon.get().getName()).isEqualTo("new");
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		Coupon foundCoupon = couponRepository.findById(testCoupon.getId()).orElse(null);
		String updateName = "update";
		ZonedDateTime updateDate = ZonedDateTime.now();
		boolean updateUsed = true;

		// when
		assert foundCoupon != null;
		foundCoupon.setName(updateName);
		foundCoupon.setExpireDate(updateDate);
		foundCoupon.setUsed(updateUsed);
		couponRepository.save(foundCoupon);

		// then
		assertEquals(foundCoupon.getName(), updateName);
		assertEquals(foundCoupon.getExpireDate(), updateDate);
		assertEquals(foundCoupon.isUsed(), updateUsed);
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		Coupon savedCoupon = couponRepository.findById(testCoupon.getId()).orElse(null);

		// when
		assert savedCoupon != null;
		couponRepository.deleteById(savedCoupon.getId());
		Optional<Coupon> foundCoupon = couponRepository.findById(savedCoupon.getId());

		// then
		assertThat(foundCoupon).isNotPresent();
	}

	@Test
	@DisplayName("find all")
	void findAll() {
		// given
		Coupon newCoupon = Coupon.builder()
			.name("new")
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.isUsed(false)
			.build();

		couponRepository.save(newCoupon);

		// when
		Iterable<Coupon> coupons = couponRepository.findAll();

		// then
		assertThat(coupons).hasSize(2);
	}
}
