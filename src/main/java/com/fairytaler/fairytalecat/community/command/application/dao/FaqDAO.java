package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqDAO extends JpaRepository<Faq, Long> {
}
