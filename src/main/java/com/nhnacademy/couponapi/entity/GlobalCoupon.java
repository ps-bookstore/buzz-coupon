package com.nhnacademy.couponapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class GlobalCoupon {

    @Id
    @OneToOne
    private Coupon coupon;

    @ManyToOne
    private CouponPolicy couponPolicy;
}
