package com.fairytaler.fairytalecat.community.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "TB_INQUIRY")
@SequenceGenerator(
        name = "SEQ_INQUIRY_CODE"
        , sequenceName = "SEQ_INQUIRY_CODE"
        , initialValue = 1
        , allocationSize = 1
)
public class Inquiry {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_INQUIRY_CODE"
    )
    private Long inquiryCode;
    private String title;
    private String content;
    private String answer;
    private String memberCode;
    private Date createDate;
    private Date answerDate;

    public Inquiry(){}
}
