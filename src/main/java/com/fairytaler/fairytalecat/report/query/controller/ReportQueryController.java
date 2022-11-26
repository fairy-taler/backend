package com.fairytaler.fairytalecat.report.query.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.report.query.service.ReportQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportQueryController {
    private static ReportQueryService reportQueryService;

    public ReportQueryController(ReportQueryService reportQueryService){
        this.reportQueryService = reportQueryService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> selectReportListByPaging(Pageable pageable){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "신고 전체 조회 성공", reportQueryService.getReportListWidthPaging(pageable)));
    }
    @GetMapping("/{reportCode}")
    public ResponseEntity<ResponseDTO> selectReportListByPaging(@PathVariable Long reportCode){
        System.out.println(reportCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "신고 상세 조회 성공", reportQueryService.getReport(reportCode)));
    }
    /* 동화 id로 신고 목록 검색 */
    @GetMapping("/taleId")
    public ResponseEntity<ResponseDTO> selectReportListByPaging(Pageable pageable,@RequestParam String id){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "신고 전체 조회 성공", reportQueryService.getReportListWidthPagingAndTaleId(id, pageable)));
    }
}
