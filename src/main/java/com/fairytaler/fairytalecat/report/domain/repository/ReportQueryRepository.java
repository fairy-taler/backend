package com.fairytaler.fairytalecat.report.domain.repository;

import com.fairytaler.fairytalecat.report.domain.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportQueryRepository  extends JpaRepository<Report, Long> {
}
