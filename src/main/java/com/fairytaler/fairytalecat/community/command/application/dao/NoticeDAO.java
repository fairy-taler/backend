package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDAO extends JpaRepository<Notice, Long> {
}
