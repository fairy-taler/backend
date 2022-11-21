package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "tale")
@ToString
@AllArgsConstructor
public class Tale {

    @Id
    private String id;              // 고유 번호
    private String memberCode;      // 작성자 코드
    private String title;           // 제목
    private Date createAt;         // 생성 날짜
    private List<TalePage> pages;   // 페이지

    public Tale(){}

}
