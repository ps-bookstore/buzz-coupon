package store.buzzbook.coupon.repository;

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

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.entity.constant.CouponRange;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class SpecificCouponRepositoryTest {

	@Autowired
	private SpecificCouponRepository specificCouponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponType testCouponType;
	private CouponPolicy testCouponPolicy;
	private SpecificCoupon testSpecificCoupon;

	@BeforeEach
	public void setUp() {
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

		testSpecificCoupon = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy)
			.bookId(1L)
			.build();

		couponTypeRepository.save(testCouponType);
		couponPolicyRepository.save(testCouponPolicy);
		specificCouponRepository.save(testSpecificCoupon);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		SpecificCoupon newSpecificCoupon = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy)
			.bookId(1L)
			.build();

		// when
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
