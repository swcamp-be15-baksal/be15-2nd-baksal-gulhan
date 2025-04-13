package com.hanhwa_tae.gulhan.user.me.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsOrderHistoryDTO {
    /*      o.order_id,
            o.ordered_at,
            oh.quantity,
            g.title,
            g.detail,
            g.price,
            o.shipping_no,
            o.address,
            o.receiver,
            o.receiver_phone,
            oh.is_confirmed*/

    private int orderId;
    private LocalDateTime orderedAt;
    private int quantity;
    private String title;
    private String detail;
    private int price;
    private String shippingNo;
    private String address;
    private String receiver;
    private String receiverPhone;
    private LocalDateTime arrivalDate;
    private String isConfirmed;

}
