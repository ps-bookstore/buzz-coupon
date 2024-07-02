package store.buzzbook.coupon.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import store.buzzbook.coupon.common.constant.CouponScope;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpecificCouponRepositoryTest {

	@Autowired
	private SpecificCouponRepository specificCouponRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponPolicy testCouponPolicy;
	private SpecificCoupon testSpecificCoupon;

	@BeforeEach
	public void setUp() {
		CouponType testCouponType = CouponType.builder()
			.name(CouponScope.BOOK)
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

		testSpecificCoupon = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy)
			.bookId(1)
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
			.bookId(2)
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
}

