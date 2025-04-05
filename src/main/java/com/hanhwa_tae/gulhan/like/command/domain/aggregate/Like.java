package com.hanhwa_tae.gulhan.like.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    private int targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; // enum : 'PACKAGE','PLACE','GOODS'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo; // FK
}