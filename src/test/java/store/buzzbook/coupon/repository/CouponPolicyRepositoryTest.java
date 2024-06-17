package store.buzzbook.coupon.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CouponPolicyRepositoryTest {

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponType testCouponType;
	private CouponPolicy testCouponPolicy;

	@BeforeEach
	void setUp() {
		testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
			.build();

		testCouponPolicy = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountAmount(10000)
			.discountRate(1.0)
			.startDate(ZonedDateTime.now())
			.endDate(ZonedDateTime.now().plusDays(1))
			.name("test")
			.maxDiscountAmount(10000)
			.build();

		couponTypeRepository.save(testCouponType);
		couponPolicyRepository.save(testCouponPolicy);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given

		// when
		Optional<CouponPolicy> optionalCouponPolicy = couponPolicyRepository.findById(testCouponPolicy.getId());

		// then
		assertTrue(optionalCouponPolicy.isPresent());
		assertEquals(optionalCouponPolicy.get().getName(), testCouponPolicy.getName());
		assertEquals(optionalCouponPolicy.get().getDiscountAmount(), testCouponPolicy.getDiscountAmount());
		assertEquals(optionalCouponPolicy.get().getMaxDiscountAmount(), testCouponPolicy.getMaxDiscountAmount());
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		CouponPolicy savedCouponPolicy = couponPolicyRepository.findById(testCouponPolicy.getId()).orElse(null);
		String updateName = "update";
		int updateDiscountAmount = 1000;

		// when
		assert savedCouponPolicy != null;
		savedCouponPolicy.setName(updateName);
		savedCouponPolicy.setDiscountAmount(updateDiscountAmount);
		CouponPolicy updatedCouponPolicy = couponPolicyRepository.save(savedCouponPolicy);

		// then
		assertEquals(updatedCouponPolicy.getName(), updateName);
		assertEquals(updatedCouponPolicy.getDiscountAmount(), updateDiscountAmount);
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		CouponPolicy savedCouponPolicy = couponPolicyRepository.findById(testCouponPolicy.getId()).orElse(null);

		// when
		assert savedCouponPolicy != null;
		couponPolicyRepository.delete(savedCouponPolicy);
		Optional<CouponPolicy> optionalCouponPolicy = couponPolicyRepository.findById(savedCouponPolicy.getId());

		// then
		assertTrue(optionalCouponPolicy.isEmpty());
	}

	@Test
	@DisplayName("find all")
	void findAll() {
		// given
		CouponPolicy newCouponPolicy = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountAmount(10000)
			.discountRate(1.0)
			.startDate(ZonedDateTime.now())
			.endDate(ZonedDateTime.now().plusDays(1))
			.name("new")
			.maxDiscountAmount(10000)
			.build();

		couponPolicyRepository.save(newCouponPolicy);

		// when
		List<CouponPolicy> couponPolicies = couponPolicyRepository.findAll();

		// then
		assertEquals(2, couponPolicies.size());
	}
}
