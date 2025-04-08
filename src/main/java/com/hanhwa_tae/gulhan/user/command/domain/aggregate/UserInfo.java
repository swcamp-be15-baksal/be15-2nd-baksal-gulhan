package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "user_info")
@Getter
public class UserInfo {

    @Id
    private int userNo; // 1:1 OneToOne 사용..? 1:1 설정하는 방법 잘 모르겠음...

    @Temporal(TemporalType.DATE)
    private Date birth;

    private int point;

    @Column(unique = true)
    private String phone;

    @CreatedDate
    private Timestamp created_at;

    @LastModifiedDate
    private Timestamp updated_at;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // 삭제여부 soft

    private String address;

    private String countryCode;
}