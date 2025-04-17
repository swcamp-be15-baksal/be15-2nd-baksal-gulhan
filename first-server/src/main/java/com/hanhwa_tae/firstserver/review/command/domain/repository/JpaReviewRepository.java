package com.hanhwa_tae.firstserver.review.command.domain.repository;

import com.hanhwa_tae.firstserver.review.command.domain.aggregate.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends JpaRepository<Review, Integer> {
}
