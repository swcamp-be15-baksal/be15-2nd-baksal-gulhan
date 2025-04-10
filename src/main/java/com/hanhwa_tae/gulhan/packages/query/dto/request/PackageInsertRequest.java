package com.hanhwa_tae.gulhan.packages.query.dto.request;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PackageInsertRequest {

    private String title;

    private int price;

    private String detail;

    private int capacity;

    private String area;

    private Timestamp startDate;

    private Timestamp endDate;

    private String guideName;

    private String guideEmail;

    private GenderType guideGender;

    private String guidePhone;

}
