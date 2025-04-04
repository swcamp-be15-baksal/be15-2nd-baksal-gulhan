package com.hanhwa_tae.gulhan.place.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeId;

    private String title;

    @Lob
    private String detail;

    private String image;

    private String address;

    private String location; // point 타입은 어떻게 해야되는지 잘 모르겠음...

    private String restDate;

    @Enumerated(EnumType.STRING)
    private Category category; //enum : 'MUSEUM','HISTORIC_SITE','FOLK_VILLAGE'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area areaId; // FK
}
