package com.nhnacademy.couponapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private ZonedDateTime createAt;

    @Column(nullable = false)
    private ZonedDateTime expireAt;

    @Column(nullable = false)
    private boolean isUsed;
}
