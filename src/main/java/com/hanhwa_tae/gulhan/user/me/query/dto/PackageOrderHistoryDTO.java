package com.hanhwa_tae.gulhan.user.me.query.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PackageOrderHistoryDTO {

    private int orderId;
    private LocalDateTime orderedAt;
    private int quantity;
    private String title;
    private String detail;
    private int price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String guideName;
    private String isConfirmed;

}
