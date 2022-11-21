package com.fairytaler.fairytalecat.report.command.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.report.command.dto.InsertReportDTO;
import com.fairytaler.fairytalecat.report.command.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private static ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> insertReport(@RequestHeader String accessToken, @ModelAttribute InsertReportDTO insertReportDTO) throws IOException {
        System.out.println(insertReportDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "신고 입력 성공", reportService.insertReport(accessToken,insertReportDTO)));
    }
}
