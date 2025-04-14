package com.hanhwa_tae.firstserver.like.command.domain.aggregate;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "`like`"/*, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"targetId", "targetType", "user_no"})
}*/)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    private int targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType; // enum : 'PACKAGE','PLACE','GOODS'


    private String userId;


    public Like(int targetId, TargetType targetType, String userId) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.userId = userId;
    }
}