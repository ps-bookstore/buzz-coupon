package com.nhnacademy.couponapi.entity;

import java.time.ZonedDateTime;

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
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Setter
	@Column(nullable = false, length = 20)
	private String name;

	@Column(nullable = false)
	private ZonedDateTime createDate;

	@Setter
	@Column
	private ZonedDateTime expireDate;

	@Setter
	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean isUsed;
}
