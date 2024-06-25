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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import store.buzzbook.coupon.entity.CouponPolicy;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.entity.constant.DiscountType;
import store.buzzbook.coupon.repository.couponpolicy.CouponPolicyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
		couponTypeRepository.save(testCouponType);

		testCouponPolicy = CouponPolicy.builder()
			.couponType(testCouponType)
			.standardPrice(1000)
			.discountType(DiscountType.AMOUNT)
			.discountAmount(10000)
			.discountRate(1.0)
			.period(14)
			.startDate(ZonedDateTime.now())
			.endDate(ZonedDateTime.now().plusDays(1))
			.name("test")
			.maxDiscountAmount(10000)
			.isDeleted(false)
			.build();
		couponPolicyRepository.save(testCouponPolicy);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given

		// when
		CouponPolicy savedCouponPolicy = couponPolicyRepository.save(testCouponPolicy);

		// then
		assertEquals(testCouponPolicy.getName(), savedCouponPolicy.getName());
		assertEquals(testCouponPolicy.getDiscountAmount(), savedCouponPolicy.getDiscountAmount());
		assertEquals(testCouponPolicy.getMaxDiscountAmount(), savedCouponPolicy.getMaxDiscountAmount());
	}

	@Test
	@DisplayName("update")
	void update() {
		// given
		CouponPolicy savedCouponPolicy = couponPolicyRepository.save(testCouponPolicy);
		ZonedDateTime updatedDate = ZonedDateTime.now().plusDays(2);

		// when
		savedCouponPolicy.changeEndDate(updatedDate);
		CouponPolicy updatedCouponPolicy = couponPolicyRepository.save(savedCouponPolicy);

		// then
		assertEquals(updatedCouponPolicy.getEndDate(), savedCouponPolicy.getEndDate());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given
		CouponPolicy savedCouponPolicy = couponPolicyRepository.save(testCouponPolicy);

		// when
		couponPolicyRepository.deleteById(savedCouponPolicy.getId());
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
			.discountType(DiscountType.AMOUNT)
			.discountAmount(10000)
			.discountRate(1.0)
			.period(14)
			.startDate(ZonedDateTime.now())
			.endDate(ZonedDateTime.now().plusDays(1))
			.name("new")
			.maxDiscountAmount(10000)
			.isDeleted(false)
			.build();

		couponPolicyRepository.save(newCouponPolicy);

		// when
		List<CouponPolicy> couponPolicies = couponPolicyRepository.findAll();

		// then
		assertEquals(2, couponPolicies.size());
	}

	@Test
	@DisplayName("find all by paging")
	void findAllByPaging() {
		// given
		Pageable pageable = PageRequest.of(0, 10);

		// when
		Page<CouponPolicy> result = couponPolicyRepository.findAllBy(pageable);

		// then
		assertTrue(result.hasContent());
	}
}
