package com.fairytaler.fairytalecat.report.domain.repository;

import com.fairytaler.fairytalecat.report.domain.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportQueryRepository  extends JpaRepository<Report, Long> {
    List<Report> findByTargetTaleCode(String id);
    Page<Report> findByTargetTaleCode(String id, Pageable pageable);

}
