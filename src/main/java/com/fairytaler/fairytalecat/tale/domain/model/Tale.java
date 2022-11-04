package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "tale")
public class Tale {

    @Id
    private String id;              // 고유 번호
    private String memberCode;      // 작성자 코드
    private String title;           // 제목
    private String createAt;        // 생성 날짜
    private List<TalePage> pages;   // 페이지
}


