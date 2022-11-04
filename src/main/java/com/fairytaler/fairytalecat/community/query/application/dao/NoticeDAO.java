package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDAO extends JpaRepository<Notice, Long> {
}
