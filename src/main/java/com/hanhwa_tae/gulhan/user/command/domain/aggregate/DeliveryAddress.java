package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_address")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryAddressId;

    private String address;

    private String receiver;

    private String receiverPhone;

    @ManyToOne // 한명의 회원은 여러 주소를 가질수있다.
    @JoinColumn(name = "userNo")
    private User user; // FK
}