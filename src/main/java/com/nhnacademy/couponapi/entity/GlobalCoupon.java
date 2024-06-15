package com.nhnacademy.couponapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class GlobalCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Coupon coupon;

    @ManyToOne(optional = false)
    private CouponPolicy couponPolicy;
}
