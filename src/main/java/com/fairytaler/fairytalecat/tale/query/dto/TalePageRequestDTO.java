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
public class TalePageRequestDTO {

    private String page;

    private List<String> data;

    private String ttsText;

    private byte[] rawImg;

    public byte[] voice;

    public TalePageRequestDTO(){}
}