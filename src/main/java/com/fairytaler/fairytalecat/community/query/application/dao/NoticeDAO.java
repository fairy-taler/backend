package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeDAO extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeCode(Long noticeCode);
}
