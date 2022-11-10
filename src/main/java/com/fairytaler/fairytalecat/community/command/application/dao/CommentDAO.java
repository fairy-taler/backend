package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Long> {
}
