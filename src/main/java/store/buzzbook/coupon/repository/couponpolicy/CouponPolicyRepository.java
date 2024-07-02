package store.buzzbook.coupon.repository.couponpolicy;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.entity.CouponPolicy;

/**
 * 쿠폰 정책에 대한 JPA 레포지토리 인터페이스입니다.
 * <p>
 * 이 인터페이스는 기본적인 CRUD 메서드와 커스텀 QueryDSL 메서드를 제공합니다.
 * </p>
 */
public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Integer>, CouponPolicyQuerydslRepository {

	/**
	 * 페이징 처리된 모든 쿠폰 정책을 조회합니다.
	 *
	 * @param pageable 페이징 정보
	 * @return 페이징 처리된 쿠폰 정책 리스트
	 */
	Page<CouponPolicy> findAllBy(Pageable pageable);

	/**
	 * 주어진 이름의 쿠폰 정책이 존재하는지 확인합니다.
	 *
	 * @param name 쿠폰 정책의 이름
	 * @return 쿠폰 정책의 존재 여부
	 */
	Boolean existsByName(String name);

	/**
	 * 주어진 이름의 쿠폰 정책을 조회합니다.
	 *
	 * @param name 쿠폰 정책의 이름
	 * @return 주어진 이름에 해당하는 쿠폰 정책의 Optional 객체
	 */
	Optional<CouponPolicy> findByName(String name);
}
