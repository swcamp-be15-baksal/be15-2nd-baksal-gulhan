package com.hanhwa_tae.gulhan.packages.query.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PackageDTO {
    private int packageId;

    private String title;

    private int price;

    private String detail;

    private int capacity;

    private int currentRegist;

    private String area;

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp createdAt;

    private String guideName;

    private String guidePhone;

    private int remaining;

}
