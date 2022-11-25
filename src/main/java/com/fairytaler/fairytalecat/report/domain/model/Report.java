package com.fairytaler.fairytalecat.report.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@ToString
@Table(name="TB_REPORT")
@SequenceGenerator(
        name = "SEQ_REPORT_CODE"
        , sequenceName = "SEQ_REPORT_CODE"
        , initialValue = 1
        , allocationSize = 1
)
public class Report {
    @Id
    @Column(name="REPORT_CODE")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_REPORT_CODE"
    )
    private Long reportCode;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="REPORTER_CODE")
    private Long reporterCode;

    @Column(name="TARGET_CODE")
    private Long targetCode;

    @Column(name="TARGET_TALE_CODE")
    private String targetTaleCode;

    @Column(name="CONTENT")
    private String content;

    @Column(name="ATTACHMENTS")
    private String attachment;

    @Column(name="CREATE_DATE")
    private Date createDate;
}
