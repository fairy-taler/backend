package com.fairytaler.fairytalecat.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class InsertReportDTO {
    private String category;             //신고 카테고리
    private Long targetCode;           //신고 대상 회원 코드
    private String targetTaleCode;       //신고 동화 모드
    private String content;              //신고 내용
    private MultipartFile attachment;    //첨부 파일

    public InsertReportDTO(){}
}
