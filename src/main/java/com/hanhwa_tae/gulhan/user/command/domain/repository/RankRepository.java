package com.hanhwa_tae.gulhan.user.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RankType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {
    Rank findByRankName(RankType rankName);
}
