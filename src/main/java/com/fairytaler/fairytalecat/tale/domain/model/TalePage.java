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

}