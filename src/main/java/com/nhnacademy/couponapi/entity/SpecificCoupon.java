package com.nhnacademy.couponapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class SpecificCoupon {

    @Id
    @OneToOne(optional = false)
    private Coupon coupon;

    @ManyToOne(optional = false)
    private CouponPolicy couponPolicy;

    @NotNull
    private long bookId;
}
