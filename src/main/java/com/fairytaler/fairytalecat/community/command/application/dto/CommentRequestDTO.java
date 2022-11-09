package com.fairytaler.fairytalecat.community.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CommentRequestDTO {
    private String content;
    private Long forumCode;

    public CommentRequestDTO(){}
}
