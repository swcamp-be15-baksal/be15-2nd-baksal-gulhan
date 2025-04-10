package com.hanhwa_tae.gulhan.packages.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageId;

    private String title;

    private int price;

    @Lob
    private String detail;

    private int capacity;

    private int currentRegist;

    private Timestamp startDate;

    private Timestamp endDate;

    @CreatedDate
    private Timestamp createAt;

    private String guideName;

    private String guideEmail;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private String guidePhone;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;
}