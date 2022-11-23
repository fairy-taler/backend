package com.fairytaler.fairytalecat.report.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@ToString
public class ReporMetaDataDTO {
    private Long reportCode;           //신고 번호
    private String category;           //신고 카테고리
    private Long reporterCode;         //신고 신청 회원 코드
    private String reporterId;         //신고 신청 회원 아이디
    private Long targetCode;           //신고 대상 회원 코드
    private String targetId;           //신고 대상 회원 아이디
    private String targetTaleCode;     //신고 대상 동화 코드
    private String targetTaleTitle;    //신고 대상 동화 이름
    private Date createDate;           //신고 신청 일자

    public ReporMetaDataDTO(){}

}
