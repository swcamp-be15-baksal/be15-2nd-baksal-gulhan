package com.hanhwa_tae.secondserver.dashboard.query.dto.response;

import lombok.Getter;

@Getter
public class OrderAllListDTO {

    private String orderId;  //int

    private String orderedAt;

    private int totalPrice;

    private int totalPoint;

    private int totalAmount;

    private String shippingNo;

    private String address;

    private String receiver;

    private String receiverPhone;

    private String orderCode;

    private int userNo;

    private String userName;
}
