package com.nhnacademy.couponapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class UserCoupon {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private Coupon coupon;

    @NotNull
    private long userId;
}
