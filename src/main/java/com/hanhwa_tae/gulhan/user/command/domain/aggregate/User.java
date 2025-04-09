package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank; // 기본 rankId =2 평민, FK

    public void setDefaultRank(Rank rank) {
        this.rank = rank;
    }
}