package com.fairytaler.fairytalecat.community.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class InquiryRequestDTO {
    private Long inquiryCode;
    private String title;
    private String content;
    private String answer;
}
