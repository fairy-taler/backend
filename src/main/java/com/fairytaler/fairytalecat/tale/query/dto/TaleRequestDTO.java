package com.fairytaler.fairytalecat.tale.query.dto;

import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleRequestDTO {
    private String id;
    private String title;
    private List<TalePageRequestDTO> pages;
    public TaleRequestDTO() {}
}