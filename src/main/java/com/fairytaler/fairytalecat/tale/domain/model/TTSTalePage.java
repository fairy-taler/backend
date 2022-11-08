package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class TTSTalePage {

    private String page;

    private List<String> data;

    private String ttsText;

}