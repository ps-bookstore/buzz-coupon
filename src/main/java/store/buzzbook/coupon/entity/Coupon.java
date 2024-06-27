package store.buzzbook.coupon.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.buzzbook.coupon.common.constant.CouponStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_policy_id")
	private CouponPolicy couponPolicy;

	@NotNull
	private LocalDate createDate;

	@NotNull
	private LocalDate expireDate;

	@NotNull
	@Enumerated(value = EnumType.STRING)
	private CouponStatus status;

	public void changeStatus(CouponStatus newStatus) {
		this.status = newStatus;
	}
}
