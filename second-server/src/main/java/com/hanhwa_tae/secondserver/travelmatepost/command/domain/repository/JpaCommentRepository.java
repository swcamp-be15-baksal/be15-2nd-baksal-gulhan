package com.hanhwa_tae.secondserver.travelmatepost.command.domain.repository;

import com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Integer> {
}
