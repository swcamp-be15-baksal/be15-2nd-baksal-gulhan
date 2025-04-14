package com.hanhwa_tae.secondserver.travelmatepost.command.domain.repository;

import com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate.TravelMatePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTravelMatePostRepository extends JpaRepository<TravelMatePost, Integer> {
}
