package store.buzzbook.coupon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 쿠폰 엔티티 클래스입니다.
 * <p>
 * 이 클래스는 카테고리 쿠폰의 ID, 쿠폰 정책, 카테고리 ID를 포함합니다.
 * </p>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CategoryCoupon {

	/**
	 * 카테고리 쿠폰의 ID 입니다.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 쿠폰 정책을 나타내는 CouponPolicy 객체입니다.
	 * <p>
	 * 쿠폰 정책은 LAZY 로딩 전략을 사용하여 필요할 때 로드됩니다.
	 * </p>
	 */
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_policy_id")
	private CouponPolicy couponPolicy;

	/**
	 * 카테고리 ID 입니다.
	 * <p>
	 * 카테고리 ID는 1 이상이어야 합니다.
	 * </p>
	 */
	@Min(1)
	private int categoryId;
}
