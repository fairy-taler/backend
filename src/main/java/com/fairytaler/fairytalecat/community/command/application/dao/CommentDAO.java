package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment, Long> {
    List<Comment> findByForumCode(Long forumCode);
}
