package com.fairytaler.fairytalecat.report.command.service;

import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.report.command.dto.InsertReportDTO;
import com.fairytaler.fairytalecat.report.domain.model.Report;
import com.fairytaler.fairytalecat.report.domain.repository.ReportRepository;
import com.fairytaler.fairytalecat.common.file.AwsS3InsertService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class ReportService {
    TokenProvider tokenProvider;
    ReportRepository reportRepository;
    private AwsS3InsertService awsS3InsertService;

    public ReportService(TokenProvider tokenProvider, ReportRepository reportRepository, AwsS3InsertService awsS3InsertService){
        this.reportRepository = reportRepository;
        this.awsS3InsertService = awsS3InsertService;
        this.tokenProvider = tokenProvider;
    }
    public Object insertReport(String accessToken, InsertReportDTO insertReportDTO) throws IOException {
        System.out.println("[reportService] insertReport : accessToken : " + accessToken);
        Report report = new Report();
        report.setCategory(insertReportDTO.getCategory());
        report.setTargetCode(insertReportDTO.getTargetCode());
        report.setTargetTaleCode(insertReportDTO.getTargetTaleCode());
        report.setContent(insertReportDTO.getContent());
        report.setCreateDate(new Date());
        report.setReporterCode(Long.parseLong(tokenProvider.getUserCode(accessToken)));

        /* 파일 s3에 업로드 후 url 저장*/
        if(insertReportDTO.getAttachment()!= null){
            InputStream inputStream = insertReportDTO.getAttachment().getInputStream();
            String url = awsS3InsertService.uploadImage(inputStream);
            report.setAttachment(url);
        }
        reportRepository.save(report);
        return report;
    }
}
