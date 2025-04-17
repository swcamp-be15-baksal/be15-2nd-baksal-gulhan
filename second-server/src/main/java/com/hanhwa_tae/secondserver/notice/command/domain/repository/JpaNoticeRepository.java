package com.hanhwa_tae.secondserver.notice.command.domain.repository;

import com.hanhwa_tae.secondserver.notice.command.domain.aggregate.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNoticeRepository extends JpaRepository<Notice, Long> {

}
