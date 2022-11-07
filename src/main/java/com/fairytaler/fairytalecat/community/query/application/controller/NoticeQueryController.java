package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.service.NoticeQueryService;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeQueryController {
    static private TokenProvider tokenProvider;
<<<<<<< HEAD
<<<<<<< HEAD
    static private NoticeQueryService noticeQueryService;
    public NoticeQueryController(NoticeQueryService noticeQueryService, TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        this.noticeQueryService = noticeQueryService;
=======
=======
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    static private NoticeQueryService noticeService;
    public NoticeQueryController(NoticeQueryService noticeService, TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        this.noticeService = noticeService;
<<<<<<< HEAD
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
=======
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    }

    /* 공지사항 조회 */
    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<ResponseDTO> selectNotice(@PathVariable Long noticeCode){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
<<<<<<< HEAD
<<<<<<< HEAD
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", noticeQueryService.getNotice(noticeCode)));
=======
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", noticeService.getNotice(noticeCode)));
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
=======
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", noticeService.getNotice(noticeCode)));
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    }

    /* 공지사항 전체 조회 */
    @GetMapping("/notices")
<<<<<<< HEAD
<<<<<<< HEAD
    public ResponseEntity<ResponseDTO> selectNoticeListWithPaging(Pageable pageable){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", noticeQueryService.getNoticeListWidthPaging(pageable)));
=======
=======
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    public ResponseEntity<ResponseDTO> selectNoticeListWithPaging(@RequestParam(name="isPublic", defaultValue="N") String isPublic){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", isPublic));
<<<<<<< HEAD
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
=======
>>>>>>> 6cb2edb410ca478b1a0aa4cc51b1687a451b64b9
    }

}
