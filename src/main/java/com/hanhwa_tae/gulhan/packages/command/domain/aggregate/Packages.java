package com.hanhwa_tae.gulhan.packages.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Gender;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Table(name = "package")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageId;

    private String title;

    private int price;

    @Lob
    private String detail;

    private int capacity;

    private int currentRegist;

    private String area;

    private Timestamp startDate;

    private Timestamp endDate;

    @CreatedDate
    private Timestamp createdAt;

    private String guideName;

    private String guideEmail;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String guidePhone;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;
}