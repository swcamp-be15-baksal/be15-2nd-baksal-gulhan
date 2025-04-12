package com.hanhwa_tae.gulhan.user.me.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PackageOrderHistoryDTO {

    private int orderId;
    private LocalDateTime orderedAt;
    private int totalPrice;
    private int totalAmount;
    private String isConfirmed;
    private LocalDateTime arrivalDate;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String guideName;

}
