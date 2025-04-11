package com.hanhwa_tae.gulhan.packages.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

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
    private GenderType guideGender;

    private String guidePhone;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N;



    public void updatePackage(@NotBlank String title, @NotBlank int price, @NotBlank String detail,
                              @NotBlank int capacity, @NotBlank String area, @NotBlank Timestamp startDate,
                              @NotBlank Timestamp endDate, @NotBlank String guideName, @NotBlank String guideEmail,
                              @NotBlank GenderType guideGender, @NotBlank String guidePhone) {
        this.title = title;
        this.price = price;
        this.detail = detail;
        this.capacity = capacity;
        this.area = area;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guideGender = guideGender;
        this.guidePhone = guidePhone;
    }
}
