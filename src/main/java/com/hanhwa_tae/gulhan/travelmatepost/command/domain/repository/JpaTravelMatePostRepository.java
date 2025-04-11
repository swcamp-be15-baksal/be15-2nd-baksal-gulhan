package com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository;

import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.TravelMatePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTravelMatePostRepository extends JpaRepository<TravelMatePost, Integer> {
}
