package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeQueryDAO extends JpaRepository<Notice, Long> {
    Optional<Notice> findByNoticeCode(Long noticeCode);
    Page<Notice> findAll(Pageable pageable);
}
