package store.buzzbook.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.buzzbook.coupon.common.constant.CouponScope;

/**
 * 쿠폰 타입 엔티티 클래스입니다.
 * <p>
 * 이 클래스는 쿠폰 타입의 ID와 이름을 포함합니다.
 * </p>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CouponType {

	/**
	 * 쿠폰 타입의 ID 입니다.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 쿠폰 타입의 이름입니다.
	 * <p>
	 * 이름은 {@link CouponScope} enum 타입이며, null 일 수 없습니다.
	 * </p>
	 */
	@NotNull
	@Column(length = 10)
	@Enumerated(value = EnumType.STRING)
	private CouponScope name;
}
