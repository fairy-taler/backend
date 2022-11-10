package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumQueryDAO extends JpaRepository<Forum, Long> {
    Page<Forum> findByCategory(String category, Pageable pageable);
    Page<Forum> findByMemberCode(String memberCode, Pageable pageable);
}