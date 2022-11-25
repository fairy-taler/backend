package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dto.CommentResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.dto.ForumResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.service.ForumQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForumQueryController {

    static private ForumQueryService forumQueryService;

    public ForumQueryController(ForumQueryService forumQueryService){
        this.forumQueryService = forumQueryService;
    }

    /* 게시판 상세 조회 */
    @GetMapping("/forums/{forumCode}")
    public ResponseEntity<ResponseDTO> selectForum(@RequestHeader String accessToken, @PathVariable Long forumCode){
        System.out.println("forumController selectForum1 : " + forumCode);
        ForumResponseDTO forumResponseDTO = forumQueryService.getForum(accessToken, forumCode);
        if(forumResponseDTO == null){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 조회 실패", null));
        }
        System.out.println("forumController selectForum2 : " + forumResponseDTO);
        List<CommentResponseDTO> comments = forumQueryService.getCommentInForum(accessToken, forumCode);
        forumResponseDTO.setComments(comments);
        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "게시판 조회 성공", forumResponseDTO));
    }
    
    /* 게시판 전체 조회 */
    @GetMapping("/forums")
    public ResponseEntity<ResponseDTO> selectForumListWithPaging(Pageable pageable){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 조회 성공", forumQueryService. getForumListWidthPaging2(pageable)));
    }
    /* 카테고리 별 게시글 조회 */
    @GetMapping("/forums/category")
    public ResponseEntity<ResponseDTO> selectForumListByCategoryWithPaging(String category,Pageable pageable){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 조회 성공", forumQueryService. getForumListByCategoryWidthPaging(category,pageable)));
    }
    /* 내가 쓴 게시글 조회 */
    @GetMapping("/forums/my")
    public ResponseEntity<ResponseDTO> selectForumListByMemberCodeWithPaging(@RequestHeader String accessToken, Pageable pageable){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 조회 성공", forumQueryService. getForumListByMemberCodeWidthPaging(accessToken, pageable)));
    }

}
