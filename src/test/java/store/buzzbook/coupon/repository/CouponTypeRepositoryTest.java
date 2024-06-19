package store.buzzbook.coupon.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import store.buzzbook.coupon.entity.CouponType;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CouponTypeRepositoryTest {

	@Autowired
	private CouponTypeRepository couponTypeRepository;

	private CouponType testCouponType;

	@BeforeEach
	void setUp() {
		testCouponType = CouponType.builder()
			.name("book")
			.build();

		couponTypeRepository.save(testCouponType);
	}

	@Test
	@DisplayName("save")
	void save() {
		// given
		CouponType newCouponType = CouponType.builder()
			.name("category")
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
