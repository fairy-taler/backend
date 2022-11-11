package com.fairytaler.fairytalecat.community.query.application.dao;

import com.fairytaler.fairytalecat.community.domain.model.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryQueryDAO extends  JpaRepository<Inquiry, Long> {
    Page<Inquiry> findAll(Pageable pageable);
    Page<Inquiry> findByMemberCode(String memberCode,Pageable pageable);

}
