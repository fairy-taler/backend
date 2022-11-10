package com.fairytaler.fairytalecat.community.query.application.dto;

import com.fairytaler.fairytalecat.community.command.application.dto.ForumRequestDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class ForumResponseDTO{
    private Long forumCode;
    private String memberCode;
    private String title;
    private String content;

    private List<CommentResponseDTO> comments;

    public ForumResponseDTO(){}
}
