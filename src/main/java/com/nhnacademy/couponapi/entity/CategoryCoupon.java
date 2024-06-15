package com.nhnacademy.couponapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class CategoryCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Coupon coupon;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CouponPolicy couponPolicy;

    @NotNull
    private long categoryId;
}
