package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentQueryDAO extends JpaRepository<Comment, Long> {
    public List<Comment> findByForumCode(Long forumCode);
}
