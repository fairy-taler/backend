package com.fairytaler.fairytalecat.tale.query.dto;

import com.fairytaler.fairytalecat.tale.domain.model.TTSTalePage;
import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleTTSRequestDTO {
    private String title;
    private List<TTSTalePage> pages;
    public TaleTTSRequestDTO() {}
}