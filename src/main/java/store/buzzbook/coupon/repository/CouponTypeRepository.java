package store.buzzbook.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import store.buzzbook.coupon.common.constant.CouponScope;
import store.buzzbook.coupon.entity.CouponType;

/**
 * 쿠폰 타입에 대한 JPA 레포지토리 인터페이스입니다.
 * <p>
 * 이 인터페이스는 기본적인 CRUD 메서드와 추가적인 커스텀 메서드를 제공합니다.
 * </p>
 */
public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {

	/**
	 * 모든 쿠폰 타입을 조회합니다.
	 *
	 * @return 모든 쿠폰 타입 리스트
	 */
	List<CouponType> findAllBy();

	/**
	 * 주어진 이름의 쿠폰 타입을 조회합니다.
	 *
	 * @param name 쿠폰 타입의 이름
	 * @return 주어진 이름에 해당하는 쿠폰 타입의 Optional 객체
	 */
	Optional<CouponType> findAllByName(CouponScope name);

	/**
	 * 주어진 이름의 쿠폰 타입이 존재하는지 확인합니다.
	 *
	 * @param name 쿠폰 타입의 이름
	 * @return 쿠폰 타입의 존재 여부
	 */
	Boolean existsByName(CouponScope name);
}
