package store.buzzbook.coupon.repository.couponpolicy;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CouponPolicy;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Integer>, CouponPolicyQuerydslRepository {

	Page<CouponPolicy> findAllBy(Pageable pageable);

	boolean existsByName(String name);
	
	Optional<CouponPolicy> findByName(String name);
}
