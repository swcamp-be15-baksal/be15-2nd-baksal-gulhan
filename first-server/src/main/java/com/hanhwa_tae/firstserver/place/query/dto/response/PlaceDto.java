package com.hanhwa_tae.firstserver.place.query.dto.response;

import com.hanhwa_tae.firstserver.place.command.domain.aggregate.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDto {
    private Long placeId;
    private String title;
    private String detail;
    private String image;
    private String address;
    private String restDate;
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    private String areaName;

}
