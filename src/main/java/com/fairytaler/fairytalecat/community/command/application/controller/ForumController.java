package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.CommentRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.FaqRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.ForumRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.ForumService;
import com.fairytaler.fairytalecat.community.command.domain.model.Comment;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.command.domain.model.Forum;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForumController {
    static private ForumService forumService;

    public ForumController(ForumService forumService){
        this.forumService=forumService;
    }
    /* 게시글 입력 */
    @PostMapping("/forums")
    public ResponseEntity<ResponseDTO> insertForum(@RequestHeader String accessToken, @RequestBody ForumRequestDTO forumRequestDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시글 입력 성공", forumService.insertForum(accessToken,forumRequestDTO)));
    }
    /* 게시글에 댓글 입력*/
    @PostMapping("/forums/comments")
    public ResponseEntity<ResponseDTO> insertCommentInForum(@RequestHeader String accessToken, @RequestBody CommentRequestDTO commentRequestDTO){
        System.out.println(commentRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "댓글 입력 성공", forumService.insertComment(accessToken,commentRequestDTO)));
    }

    /* 게시글 수정 */
    @PutMapping("/forums")
    public ResponseEntity<ResponseDTO> updateFaq(@RequestHeader String accessToken, @RequestBody ForumRequestDTO forumRequestDTO){

        Forum forum = forumService.updateForum(accessToken, forumRequestDTO);
        if(forum == null) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,  forumRequestDTO.getForumCode() + "번 게시글이 존재하지 않음", forumRequestDTO.getForumCode()));
    }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시글 수정 성공", forum));
    }

    /* 게시글 삭제 */

    /* 댓글 수정 */
    @PutMapping("/forums/comments")
    public ResponseEntity<ResponseDTO> updateComment(@RequestHeader String accessToken, @RequestBody CommentRequestDTO commentRequestDTO){

        Comment comment = forumService.updateComment(accessToken, commentRequestDTO);
        if(comment == null) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,  commentRequestDTO.getCommentCode() + "번 댓글이 존재하지 않음", commentRequestDTO.getCommentCode()));
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시글 댓글 성공", comment));
    }
    /* 댓글 삭제*/
    @DeleteMapping("/forums/comments/{commentCode}")
    public ResponseEntity<ResponseDTO> deleteComment(@RequestHeader String accessToken,@PathVariable Long commentCode){
        Long result = forumService.deleteComment(accessToken, commentCode);
        System.out.println(result);
        if(result == null){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "댓글 삭제 실패", commentCode + "번 댓글이 존재하지 않습니다."));
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "댓글 삭제 성공", result));
    }

}
