package com.fairytaler.fairytalecat.tale.query.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TaleVoiceRequestDTO{
    private String title;
    private List<TaleVoicePage> pages;

    public TaleVoiceRequestDTO(){}

}
