package com.fairytaler.fairytalecat.community.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class FaqRequestDTO {

    private Long faqCode;
    private String title;
    private String content;
    private String answer;

    public FaqRequestDTO(Long faqCode, String title, String content, String answer) {
        this.faqCode = faqCode;
        this.title = title;
        this.content = content;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "FaqRequestDTO{" +
                "faqCode=" + faqCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
