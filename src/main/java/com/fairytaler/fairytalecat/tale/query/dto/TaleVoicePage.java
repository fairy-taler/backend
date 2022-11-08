package com.fairytaler.fairytalecat.tale.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleVoicePage {
    private String page;

    private List<String> data;

    private MultipartFile voice;

    public TaleVoicePage(){}
}
