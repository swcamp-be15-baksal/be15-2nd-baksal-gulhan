package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "order_history")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderHistoryId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; // enum : 'GOODS','PACKAGE'

    private int targetId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Confirm isConfirmed = Confirm.N; // enum : Y, N

    @CreatedDate
    private Timestamp arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order orderId; // FK
}