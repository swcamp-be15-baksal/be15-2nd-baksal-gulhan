package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "order_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderHistoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_history_type")
    private TargetType targetType; // enum : 'GOODS','PACKAGE'
    private int targetId;
    private int quantity;


    @CreatedDate
    private Timestamp arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId; // FK

    @Builder
    public OrderHistory(TargetType targetType, int quantity, int targetId,
                        Order orderId){
        this.targetType = targetType;
        this.targetId = targetId;
        this.quantity = quantity;
        this.orderId = orderId;
    }
}