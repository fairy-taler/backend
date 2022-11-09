package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.CommentRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.ForumRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController {
    static private ForumService forumService;

    public ForumController(ForumService forumService){
        this.forumService=forumService;
    }

    @PostMapping("/forums")
    public ResponseEntity<ResponseDTO> insertForum(@RequestHeader String accessToken, @RequestBody ForumRequestDTO forumRequestDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 입력 성공", forumService.insertForum(accessToken,forumRequestDTO)));
    }

    @PostMapping("/forums/comment")
    public ResponseEntity<ResponseDTO> insertCommentInForum(@RequestHeader String accessToken, @RequestBody CommentRequestDTO commentRequestDTO){
        System.out.println(commentRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 입력 성공", forumService.insertComment(accessToken,commentRequestDTO)));
    }
}
