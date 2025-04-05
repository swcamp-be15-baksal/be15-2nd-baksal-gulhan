package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderId;
    @CreatedDate
    private Date orderedAt;

    private int totalPrice;

    private int totalPoint;

    private int totalAmount;

    @Column(unique = true)
    private String shippingNo;

    private String address;

    private String receiver;

    private String receiverPhone;

    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;
}