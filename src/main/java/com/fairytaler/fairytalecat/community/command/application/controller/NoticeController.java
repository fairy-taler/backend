package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.NoticeRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.NoticeService;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeController {
    static private TokenProvider tokenProvider;
    static private NoticeService noticeService;
    public NoticeController(NoticeService noticeService, TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        this.noticeService = noticeService;
    }
    
    /* 공지사항 입력 */
    @PostMapping("/notices")
    public ResponseEntity<ResponseDTO> registNotice(@RequestBody NoticeRequestDTO noticeRequestDTO){
        System.out.println(noticeRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 입력 성공", noticeService.registNotice(noticeRequestDTO)));
    }
    /* 공지사항 내용 수정*/
    @PutMapping("/notices")
    public ResponseEntity<ResponseDTO> updateNotice(@RequestBody NoticeRequestDTO noticeRequestDTO){
        System.out.println(noticeRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 수정 성공", noticeService.updateNotice(noticeRequestDTO)));
    }
    /* 공지사항 비공개*/
    @PutMapping("/notices/{noticeCode}")
    public ResponseEntity<ResponseDTO> updateNoticeToPublic(@PathVariable Long noticeCode,@RequestParam(name="isPublic", defaultValue="true") boolean isPublic){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 비공개 설정 성공",  noticeService.updateNoticeToPublic(noticeCode, isPublic)));
    }

    /* 공지사항 삭제 */
    @DeleteMapping("/notices/{noticeCode}")
    public ResponseEntity<ResponseDTO> privateNotice(@PathVariable Long noticeCode){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 삭제 성공", noticeService.deleteNotice(noticeCode)));
    }
}
