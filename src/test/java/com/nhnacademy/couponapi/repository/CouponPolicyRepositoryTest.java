package com.nhnacademy.couponapi.repository;

import com.nhnacademy.couponapi.entity.CouponPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CouponPolicyRepositoryTest {

    @Autowired
    private CouponPolicyRepository couponPolicyRepository;

    private CouponPolicy testCouponPolicy;

    @BeforeEach
    void setUp() {
        testCouponPolicy = CouponPolicy.builder()
                .name("test")
                .discountAmount(BigDecimal.TEN)
                .maxDiscountAmount(BigDecimal.valueOf(20000))
                .standardPrice(BigDecimal.TEN)
                .build();

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
        BigDecimal updateDiscountAmount = BigDecimal.ONE;

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
                .name("test")
                .discountRate(0.8)
                .discountAmount(BigDecimal.valueOf(0.00))
                .maxDiscountAmount(BigDecimal.valueOf(20000))
                .standardPrice(BigDecimal.TEN)
                .build();

        couponPolicyRepository.save(newCouponPolicy);

        // when
        List<CouponPolicy> couponPolicies = couponPolicyRepository.findAll();

        // then
        assertEquals(2, couponPolicies.size());
    }
}
