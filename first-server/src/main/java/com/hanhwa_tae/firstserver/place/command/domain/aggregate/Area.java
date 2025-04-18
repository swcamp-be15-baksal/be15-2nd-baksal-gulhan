package com.hanhwa_tae.firstserver.place.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    private String areaCode;

    private String areaName;

    @ManyToOne(fetch = FetchType.LAZY) // 부모 댓글 (대댓글을 포함한 댓글에 대한 참조)
    @JoinColumn(name = "parent_area_id")  // 부모 댓글의 외래 키
    private Area parentAreaId;
}