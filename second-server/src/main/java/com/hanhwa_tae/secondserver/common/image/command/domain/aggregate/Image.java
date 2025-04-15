package com.hanhwa_tae.secondserver.common.image.command.domain.aggregate;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgId;

    private int targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; // enum : 'PACKAGE','GOODS'

    private String imgUrl;

    private String imgOriginalName;

    @Column(unique = true)
    private String imgUuidName;
}