package store.buzzbook.coupon.repository.couponpolicy.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.SpecificCoupon;
import store.buzzbook.coupon.common.constant.CouponRange;
import store.buzzbook.coupon.common.constant.DiscountType;
import store.buzzbook.coupon.repository.CouponTypeRepository;
import store.buzzbook.coupon.repository.SpecificCouponRepository;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponPolicyQuerydslRepositoryImplTest {

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	@Autowired
	private CouponPolicyRepository couponPolicyRepository;

	@Autowired
	private SpecificCouponRepository specificCouponRepository;

	@Test
	@DisplayName("find all book id")
	void findAllByBookId() {
		// given
		CouponType testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
			.build();
		couponTypeRepository.save(testCouponType);

		CouponPolicy testCouponPolicy1 = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountType(DiscountType.AMOUNT)
			.discountAmount(3000)
			.discountRate(1.0)
			.period(14)
			.startDate(LocalDate.now())
			.endDate(LocalDate.now().plusDays(10))
			.name("test")
			.maxDiscountAmount(10000)
			.isDeleted(false)
			.build();
		couponPolicyRepository.save(testCouponPolicy1);

		CouponPolicy testCouponPolicy2 = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountType(DiscountType.AMOUNT)
			.discountAmount(3000)
			.discountRate(1.0)
			.period(14)
			.startDate(LocalDate.now())
			.endDate(LocalDate.now().plusDays(10))
			.name("test")
			.maxDiscountAmount(10000)
			.isDeleted(false)
			.build();
		couponPolicyRepository.save(testCouponPolicy2);

		SpecificCoupon testSpecificCoupon1 = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy1)
			.bookId(1)
			.build();
		specificCouponRepository.save(testSpecificCoupon1);

		SpecificCoupon testSpecificCoupon2 = SpecificCoupon.builder()
			.couponPolicy(testCouponPolicy2)
			.bookId(1)
			.build();
		specificCouponRepository.save(testSpecificCoupon2);

		// when
		List<CouponPolicy> result = couponPolicyRepository.findAllByBookId(1);

		// then
		assertEquals(2, result.size());
	}
}
