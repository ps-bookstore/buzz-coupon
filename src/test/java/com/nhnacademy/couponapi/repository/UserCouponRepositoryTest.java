package com.nhnacademy.couponapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.nhnacademy.couponapi.entity.Coupon;
import com.nhnacademy.couponapi.entity.UserCoupon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserCouponRepositoryTest {

	@Autowired
	private UserCouponRepository userCouponRepository;

	@Autowired
	private CouponRepository couponRepository;

	private UserCoupon testUserCoupon;
	private Coupon testCoupon;

	@BeforeEach
	public void setUp() {
		testCoupon = Coupon.builder()
			.name("test")
			.createDate(ZonedDateTime.now())
			.expireDate(ZonedDateTime.now().plusDays(2))
			.isUsed(false)
			.build();

		testUserCoupon = UserCoupon.builder()
			.coupon(testCoupon)
			.userId(1L)
			.build();

		couponRepository.save(testCoupon);
		userCouponRepository.save(testUserCoupon);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		UserCoupon newUserCoupon = UserCoupon.builder()
			.coupon(testCoupon)
			.userId(2L)
			.build();

		// when
		userCouponRepository.save(newUserCoupon);
		UserCoupon savedUserCoupon = userCouponRepository.findById(newUserCoupon.getId()).orElse(null);

		// then
		assert savedUserCoupon != null;
		assertEquals(savedUserCoupon.getId(), newUserCoupon.getId());
		assertEquals(newUserCoupon.getCoupon().getId(), savedUserCoupon.getCoupon().getId());
		assertEquals(newUserCoupon.getUserId(), savedUserCoupon.getUserId());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		UserCoupon deletedUserCoupon = userCouponRepository.findById(testUserCoupon.getId()).orElse(null);

		// when
		assert deletedUserCoupon != null;
		userCouponRepository.delete(deletedUserCoupon);
		UserCoupon savedUserCoupon = userCouponRepository.findById(testUserCoupon.getId()).orElse(null);

		// then
		assertTrue(Objects.isNull(savedUserCoupon));
	}

	@Test
	@DisplayName("find by id")
	void findById() {
		// given

		// when
		UserCoupon saveduserCoupon = userCouponRepository.findById(testUserCoupon.getId()).orElse(null);

		// then
		assert saveduserCoupon != null;
		assertEquals(testUserCoupon.getId(), saveduserCoupon.getId());
		assertEquals(testUserCoupon.getCoupon().getId(), saveduserCoupon.getCoupon().getId());
		assertEquals(testUserCoupon.getUserId(), saveduserCoupon.getUserId());
	}

	@Test
	@DisplayName("find by userId")
	void findByUserId() {
		// given
		UserCoupon newUserCoupon = UserCoupon.builder()
			.coupon(testCoupon)
			.userId(2L)
			.build();

		userCouponRepository.save(newUserCoupon);

		// when
		List<UserCoupon> userCoupons = userCouponRepository.findByUserId(testUserCoupon.getUserId());

		// then
		assertEquals(1, userCoupons.size());
	}
}
