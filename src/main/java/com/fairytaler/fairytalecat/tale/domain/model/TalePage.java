package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class TalePage {

    private String page;

    private List<String> data;

    private String audioUrl;

    private byte[] rawImg;          // 페이지 별 로우 이미지
    public TalePage(){}
}