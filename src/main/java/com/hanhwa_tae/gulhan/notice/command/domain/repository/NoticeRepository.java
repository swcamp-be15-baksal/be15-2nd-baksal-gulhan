package com.hanhwa_tae.gulhan.notice.command.domain.repository;

import com.hanhwa_tae.gulhan.notice.command.domain.aggregate.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
