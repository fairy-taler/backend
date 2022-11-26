package com.fairytaler.fairytalecat.tale.query.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class TaleListResponseDTO {
    private String id;
    private String title;
    private String writerId;
    private Date createAt;
    private String isBlock;
    private int reportSize;
}
