package store.buzzbook.coupon.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.buzzbook.coupon.entity.constant.DiscountType;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CouponPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CouponType couponType;

	@NotNull
	@Column(nullable = false, length = 30)
	private String name;

	@NotNull
	@Column(nullable = false)
	private DiscountType discountType;

	@NotNull
	@Column(nullable = false)
	@ColumnDefault("1.0")
	private double discountRate;

	@NotNull
	@Column(nullable = false)
	private int discountAmount;
	
	@NotNull
	@Column(nullable = false)
	private int period;

	@NotNull
	@Column(nullable = false)
	private int standardPrice;

	@NotNull
	@Column(nullable = false)
	private int maxDiscountAmount;

	@NotNull
	private ZonedDateTime startDate;

	@Setter
	@NotNull
	private ZonedDateTime endDate;
}
