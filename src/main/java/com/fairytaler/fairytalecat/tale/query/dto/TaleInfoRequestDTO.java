package com.fairytaler.fairytalecat.tale.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleInfoRequestDTO {

    private String id;
    private String fontStyle;
    private String fontSize;
    private String fontColor;
    private String coverColor;
    private String sticker;
    private String stickerPosition;
    private byte[] inputImg;
}