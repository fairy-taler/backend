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
    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<ResponseDTO> selectNotice(@PathVariable int noticeCode){
        /* 공지사항이 있으면 조회 후 반환 */

        System.out.println(noticeService.getNotice(Long.valueOf(noticeCode)));
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", noticeService.selectNotice(noticeCode)));

        /* 공지사항이 없으면 공지사항이 없습니다. 반환 */

    }
}
