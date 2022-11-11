package com.fairytaler.fairytalecat.community.command.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryDAO extends JpaRepository<Inquiry, Long> {
}
