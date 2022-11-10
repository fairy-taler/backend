package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumDAO extends JpaRepository<Forum, Long> {
}
