package com.fairytaler.fairytalecat.community.query.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentResponseDTO {
    private String nickname;
    private String content;
    private Date createDate;
    private boolean isMyComment;
    public CommentResponseDTO(){}
}
