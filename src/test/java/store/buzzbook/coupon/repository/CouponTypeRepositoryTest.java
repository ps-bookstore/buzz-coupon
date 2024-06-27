package store.buzzbook.coupon.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.common.constant.CouponRange;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponTypeRepositoryTest {

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponType testCouponType;

	@BeforeEach
	void setUp() {
		testCouponType = CouponType.builder()
			.name(CouponRange.BOOK)
			.build();

		couponTypeRepository.save(testCouponType);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		CouponType newCouponType = CouponType.builder()
			.name(CouponRange.CATEGORY)
			.build();

		couponTypeRepository.save(newCouponType);

		// when
		Optional<CouponType> savedCouponType = couponTypeRepository.findById(testCouponType.getId());

		// then
		assertTrue(savedCouponType.isPresent());
	}

	@Test
	@DisplayName("delete")
	void delete() {
		// given

		// when
		couponTypeRepository.deleteById(testCouponType.getId());
		Optional<CouponType> savedCouponType = couponTypeRepository.findById(testCouponType.getId());

		// then
		assertFalse(savedCouponType.isPresent());
	}
}
