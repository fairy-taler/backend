package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.service.NoticeQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    static private NoticeQueryService noticeService;
    public NoticeController(NoticeQueryService noticeService){
        this.noticeService = noticeService;
    }

    /* 공지사항 조회 */
    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<ResponseDTO> selectNotice(@PathVariable Long noticeCode){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공",noticeService.getNotice(noticeCode)));
    }

    @GetMapping("/notices")
    public ResponseEntity<ResponseDTO> insertNotice(@PathVariable Long noticeCode){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공",noticeService.getNotice(noticeCode)));
    }
}
