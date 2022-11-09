package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumQueryDAO extends JpaRepository<Forum, Long> {
}
