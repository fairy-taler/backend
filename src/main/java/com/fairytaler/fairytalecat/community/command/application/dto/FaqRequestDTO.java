package com.fairytaler.fairytalecat.community.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class FaqRequestDTO {

    private String title;
    private String content;
    private String answer;

    public FaqRequestDTO(String title, String conntet, String answer) {
        this.title = title;
        this.content = conntet;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "FaqRequestDTO{" +
                "title='" + title + '\'' +
                ", conntet='" + content + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
