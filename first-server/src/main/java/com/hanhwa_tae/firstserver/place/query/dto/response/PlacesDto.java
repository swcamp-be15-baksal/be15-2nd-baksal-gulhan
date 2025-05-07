package com.hanhwa_tae.firstserver.place.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlacesDto {
    private Long placeId;
    private String title;
    private String image;
    private String address;
    private String areaName;
}
