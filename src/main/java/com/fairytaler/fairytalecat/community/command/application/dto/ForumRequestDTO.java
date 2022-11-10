package com.fairytaler.fairytalecat.community.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ForumRequestDTO {
    private Long forumCode;
    private String title;
    private String content;
    private String category;

    public ForumRequestDTO(){}
}
