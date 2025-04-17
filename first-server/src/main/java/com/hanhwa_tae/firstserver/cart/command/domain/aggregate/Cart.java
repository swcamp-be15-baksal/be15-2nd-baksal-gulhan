package com.hanhwa_tae.firstserver.cart.command.domain.aggregate;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
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

    @Column(name = "user_no")
    private Long userNo; // FK

    @Builder
    public Cart(int quantity, TargetType targetType,int targetId, Long userNo){
        this.quantity = quantity;
        this.targetType = targetType;
        this.targetId = targetId;
        this.userNo = userNo;
    }

    public void updateCartCount(int quantity){
        this.quantity = quantity;
    }
}