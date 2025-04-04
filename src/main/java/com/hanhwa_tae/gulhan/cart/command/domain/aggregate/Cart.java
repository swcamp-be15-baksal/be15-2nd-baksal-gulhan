package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; // enum : 'GOODS','PACKAGE'

    private int targetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // FK
}