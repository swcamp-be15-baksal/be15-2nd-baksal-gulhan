package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(unique = true)
    private String userId;

    private String password;

    private String userName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private GenderType gender; //'M','F'

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // 삭제여부 soft

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank; // 기본 rankId =2 평민, FK


    public void setDefaultRank(Rank rank) {
        this.rank = rank;
    }

    public void setUpdateUser(String password){
        this.password = password;
    }
}