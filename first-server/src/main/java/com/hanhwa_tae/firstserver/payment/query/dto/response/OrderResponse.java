package com.hanhwa_tae.firstserver.payment.query.dto.response;


import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Confirm;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    private Date orderAt;
    private int totalPrice;
    private int totalAmount;
    private String shippingNo;
    private String address;
    private String receiver;
    private String receiverPhone;
    private String orderCode;
    private Confirm isConfirmed;
}
