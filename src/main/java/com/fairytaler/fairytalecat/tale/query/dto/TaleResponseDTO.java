package com.fairytaler.fairytalecat.tale.query.dto;

import com.fairytaler.fairytalecat.tale.domain.model.TaleInfo;
import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TaleResponseDTO {

    private TaleList taleList;
    private TaleInfo taleInfo;
}