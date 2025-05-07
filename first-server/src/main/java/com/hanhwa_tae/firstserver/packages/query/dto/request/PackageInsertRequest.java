package com.hanhwa_tae.firstserver.packages.query.dto.request;

import com.hanhwa_tae.firstserver.common.dto.GenderType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class PackageInsertRequest {

    private String title;

    private int price;

    private String detail;

    private int capacity;

    private int currentRegist;

    private int remaining;

    private String area;

    private Timestamp startDate;

    private Timestamp endDate;

    private String guideName;

    private String guideEmail;

    private GenderType guideGender;

    private String guidePhone;

    private List<String> imageUrls;

}
