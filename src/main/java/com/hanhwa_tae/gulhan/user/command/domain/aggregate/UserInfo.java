package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    private Long userNo;

    /* 반대로 조회할 일은 없지 않을까요 ..? (단방향 매핑)*/
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Temporal(TemporalType.DATE)
    private Date birth;

    private int point = 0;

    @Column(unique = true)
    private String phone;

    @CreatedDate
    private Timestamp created_at;

    @LastModifiedDate
    private Timestamp updated_at;

    private String address;

    private String countryCode = "82";

    public void setUpdateUserInfo(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }
}