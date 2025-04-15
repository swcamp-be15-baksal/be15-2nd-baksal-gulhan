package com.hanhwa_tae.firstserver.cart.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "`order`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    private String orderId;

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

    /*payment code*/
    private String orderCode;


    private Long userNo;

    @Enumerated(EnumType.STRING)
    private Confirm isConfirmed = Confirm.N; // enum : Y, N

    public void updateConfirm(Confirm isConfirmed){
        this.isConfirmed = isConfirmed;
    }


    @Builder
    public Order(String orderId, Date orderedAt, int totalPrice,
                 int totalPoint, int totalAmount,
                 String shippingNo, String address,
                 String receiver, String receiverPhone,
                 String orderCode, Long userNo,
                 Confirm isConfirmed
                 ){
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
        this.totalPoint = totalPoint;
        this.totalAmount = totalAmount;
        this.shippingNo = shippingNo;
        this.address = address;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.orderCode = orderCode;
        this.userNo = userNo;
        this.isConfirmed = isConfirmed;
    }


}