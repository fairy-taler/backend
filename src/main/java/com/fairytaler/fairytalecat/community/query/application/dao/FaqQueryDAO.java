package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaqQueryDAO extends JpaRepository<Faq, Long> {

    Optional<Faq> findByFaqCode(Long faqCode);

    Page<Faq> findAll(Pageable pageable);
}
