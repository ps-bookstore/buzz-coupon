package com.nhnacademy.couponapi.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CouponPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Setter
	@Column(nullable = false, length = 30)
	private String name;

	@Setter
	@Column(nullable = false)
	@ColumnDefault("1.0")
	private double discountRate;

	@Setter
	@Column(nullable = false, columnDefinition = "Decimal(10, 2) default `0.00`")
	private BigDecimal discountAmount;

	@Setter
	@Column(nullable = false, columnDefinition = "Decimal(10, 2) default `0.00`")
	private BigDecimal standardPrice;

	@Setter
	@Column(nullable = false, columnDefinition = "Decimal(10, 2) default `0.00`")
	private BigDecimal maxDiscountAmount;

	@Setter
	private String description;
}
