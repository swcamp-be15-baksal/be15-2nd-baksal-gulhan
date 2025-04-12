package com.hanhwa_tae.gulhan.user.me.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsOrderHistoryDTO {

    private int orderId;
    private LocalDateTime orderedAt;
    private int totalPrice;
    private int totalAmount;
    private String shippingNo;
    private String address;
    private String receiver;
    private String receiverPhone;
    private LocalDateTime arrivalDate;
    private String isConfirmed;
    private String title;
    private String detail;
}
