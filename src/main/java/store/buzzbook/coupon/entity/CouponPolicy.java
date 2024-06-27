package store.buzzbook.coupon.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import store.buzzbook.coupon.common.constant.DiscountType;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CouponPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CouponType couponType;

	@NotNull
	@Column(length = 30)
	private String name;

	@NotNull
	@Enumerated(value = EnumType.STRING)
	private DiscountType discountType;

	@NotNull
	@ColumnDefault("1.0")
	private double discountRate;

	@NotNull
	private int discountAmount;

	@NotNull
	private int period;

	@NotNull
	private int standardPrice;

	@NotNull
	private int maxDiscountAmount;

	@NotNull
	private LocalDate startDate;

	@NotNull
	private LocalDate endDate;

	@NotNull
	private boolean isDeleted;

	public void changeEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void delete() {
		this.isDeleted = true;
	}
}
