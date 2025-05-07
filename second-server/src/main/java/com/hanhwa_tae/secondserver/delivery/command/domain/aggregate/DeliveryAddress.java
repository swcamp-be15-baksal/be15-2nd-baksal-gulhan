package com.hanhwa_tae.secondserver.delivery.command.domain.aggregate;

import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryAddressId;

    private String zipcode;

    private String address;

    private String detailAddress;

    private String receiver;

    private String receiverPhone;

    @ManyToOne // 한명의 회원은 여러 주소를 가질수있다.
    @JoinColumn(name = "userNo")
    private User user; // FK

    @Enumerated(EnumType.STRING)
    private DefaultAddress defaultAddress;

}