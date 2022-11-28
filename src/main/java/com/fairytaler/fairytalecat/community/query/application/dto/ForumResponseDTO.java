package com.fairytaler.fairytalecat.community.query.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ForumResponseDTO{
    private Long forumCode;
    private String nickname;
    private String memberCode;
    private String title;
    private String content;
    private Date createDate;
    private String category;
    private String memberId;
    private String profileUrl;
    private boolean isMyForum;
    private int views;

    private List<CommentResponseDTO> comments;

    public ForumResponseDTO(){}
}
