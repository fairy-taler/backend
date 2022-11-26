package com.fairytaler.fairytalecat.report.query.service;

import com.fairytaler.fairytalecat.common.paging.Pagenation;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.report.domain.model.Report;
import com.fairytaler.fairytalecat.report.domain.repository.ReportQueryRepository;
import com.fairytaler.fairytalecat.report.query.dto.ReporMetaDataDTO;
import com.fairytaler.fairytalecat.report.query.dto.ReportResponseDTO;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryService {
    private static ReportQueryRepository reportQueryRepository;
    private static MemberInfoRepository memberInfoRepository;
    private static TaleRepository taleRepository;

    public ReportQueryService(ReportQueryRepository reportQueryRepository,MemberInfoRepository memberInfoRepository, TaleRepository taleRepository){
        this.reportQueryRepository = reportQueryRepository;
        this.memberInfoRepository = memberInfoRepository;
        this.taleRepository = taleRepository;
    }
    public Object getReportListWidthPaging(Pageable pageable){
        Page<Report> reports = reportQueryRepository.findAll(pageable);
        List<ReporMetaDataDTO> reportMetaDataList = new ArrayList<>();
        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
        for(Report report : reports.getContent()){
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            ReporMetaDataDTO reportMetaData = new ReporMetaDataDTO();

            reportMetaData.setReportCode(report.getReportCode());
            reportMetaData.setCategory(report.getCategory());
            reportMetaData.setReporterCode(report.getReporterCode());
            reportMetaData.setTargetCode(report.getTargetCode());
            reportMetaData.setTargetTaleCode(report.getTargetTaleCode());
            reportMetaData.setCreateDate(report.getCreateDate());
            try{
                /* 회원 아이디 가져오기 */
                String reporterId = memberInfoRepository.findByMemberCode(report.getReporterCode()).getMemberId();
                String targetId = memberInfoRepository.findByMemberCode(report.getTargetCode()).getMemberId();
                reportMetaData.setReporterId(reporterId);
                reportMetaData.setTargetId(targetId);
                /* 동화 제목 조회 */
                String targetTaleTitle = taleRepository.findById(report.getTargetTaleCode()).get().getTitle();
                reportMetaData.setTargetTaleTitle(targetTaleTitle);
            }
            catch(Exception e){
                System.out.println(e);
            }
            reportMetaDataList.add(reportMetaData);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<ReporMetaDataDTO> pages = new Pagenation<ReporMetaDataDTO>();
        pages.setTotalPages(reports.getTotalPages());
        pages.setTotalElement(reports.getTotalElements());
        pages.setSize(reports.getSize());
        pages.setNumber(reports.getNumber());
        pages.setContent(reportMetaDataList);

        return pages;
    }
    /* 동화 아이디로 신고 검색 */
    public Object getReportListWidthPagingAndTaleId(String id, Pageable pageable){
        Page<Report> reports = reportQueryRepository.findByTargetTaleCode(id, pageable);
        List<ReporMetaDataDTO> reportMetaDataList = new ArrayList<>();
        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
        for(Report report : reports.getContent()){
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            ReporMetaDataDTO reportMetaData = new ReporMetaDataDTO();

            reportMetaData.setReportCode(report.getReportCode());
            reportMetaData.setCategory(report.getCategory());
            reportMetaData.setReporterCode(report.getReporterCode());
            reportMetaData.setTargetCode(report.getTargetCode());
            reportMetaData.setTargetTaleCode(report.getTargetTaleCode());
            reportMetaData.setCreateDate(report.getCreateDate());
            try{
                /* 회원 아이디 가져오기 */
                String reporterId = memberInfoRepository.findByMemberCode(report.getReporterCode()).getMemberId();
                String targetId = memberInfoRepository.findByMemberCode(report.getTargetCode()).getMemberId();
                reportMetaData.setReporterId(reporterId);
                reportMetaData.setTargetId(targetId);
                /* 동화 제목 조회 */
                String targetTaleTitle = taleRepository.findById(report.getTargetTaleCode()).get().getTitle();
                reportMetaData.setTargetTaleTitle(targetTaleTitle);
            }
            catch(Exception e){
                System.out.println(e);
            }
            reportMetaDataList.add(reportMetaData);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<ReporMetaDataDTO> pages = new Pagenation<ReporMetaDataDTO>();
        pages.setTotalPages(reports.getTotalPages());
        pages.setTotalElement(reports.getTotalElements());
        pages.setSize(reports.getSize());
        pages.setNumber(reports.getNumber());
        pages.setContent(reportMetaDataList);

        return pages;
    }


    public Object getReport(Long reportCode){
        Optional<Report> oReport = reportQueryRepository.findById(reportCode);
        ReportResponseDTO reportResponseDTO= new ReportResponseDTO();
        try {
            Report report = oReport.get();
            System.out.println(report);
            /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
            reportResponseDTO.setReportCode(report.getReportCode());
            reportResponseDTO.setCategory(report.getCategory());
            reportResponseDTO.setReporterCode(report.getReporterCode());
            reportResponseDTO.setTargetCode(report.getTargetCode());
            reportResponseDTO.setTargetTaleCode(report.getTargetTaleCode());
            reportResponseDTO.setCreateDate(report.getCreateDate());
            reportResponseDTO.setAttachments(report.getAttachment());
            reportResponseDTO.setContent(report.getContent());
            /* 회원 아이디 가져오기 */
            String reporterId = memberInfoRepository.findByMemberCode(report.getReporterCode()).getMemberId();
            String targetId = memberInfoRepository.findByMemberCode(report.getTargetCode()).getMemberId();
            reportResponseDTO.setReporterId(reporterId);
            reportResponseDTO.setTargetId(targetId);
            /* 동화 제목 조회 */
            String targetTaleTitle = taleRepository.findById(report.getTargetTaleCode()).get().getTitle();
            reportResponseDTO.setTargetTaleTitle(targetTaleTitle);

        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        return reportResponseDTO;
    }

}
