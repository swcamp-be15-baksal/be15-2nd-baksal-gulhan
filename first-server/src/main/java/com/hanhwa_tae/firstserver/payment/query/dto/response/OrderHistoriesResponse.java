package com.hanhwa_tae.firstserver.payment.query.dto.response;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Confirm;
import com.hanhwa_tae.firstserver.common.domain.TargetType;

import java.sql.Timestamp;

public class OrderHistoriesResponse {
    private TargetType orderHistoryType;
    private int targetId;
    private int quantity;
    private int orderId;
    private Timestamp orderAt;
    private Confirm isConfirmed;
    private int price;
}
