package com.hanhwa_tae.secondserver.cart.command.domain.aggregate;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartItemId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    private TargetType targetType; // enum : 'GOODS','PACKAGE'

    @Column(name = "target_id")
    private int targetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // FK

    @Builder
    public Cart(int quantity, TargetType targetType,int targetId, User userNo){
        this.quantity = quantity;
        this.targetType = targetType;
        this.targetId = targetId;
        this.userNo = userNo;
    }

    public void updateCartCount(int quantity){
        this.quantity = quantity;
    }
}