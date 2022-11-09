package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.query.application.service.InquiryQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InquiryQueryController {
    static private InquiryQueryService inquiryQueryService;

    public InquiryQueryController(InquiryQueryService inquiryQueryService){
        this.inquiryQueryService = inquiryQueryService;
    }
    /* 문의 전체 조회 */
    @GetMapping("/inquirys")
    public ResponseEntity<ResponseDTO> selectInquiryListWithPaging(Pageable pageable){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "문의 조회 성공", inquiryQueryService. getInquiryListWidthPaging(pageable)));
    }

    /* 문의 상세 조회 */
    @GetMapping("/inquirys/{inquiryCode}")
    public ResponseEntity<ResponseDTO> selectInquiry(@PathVariable Long inquiryCode){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", inquiryQueryService.getInquiry(inquiryCode)));
    }
    /* 내가 작성한 문의 조회 */
    @GetMapping("/inquirys/my")
    public ResponseEntity<ResponseDTO> selectInquiryByMemberCode(@RequestHeader String accessToken,Pageable pageable){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "공지사항 조회 성공", inquiryQueryService.getInquiryByMemberCode(accessToken,pageable)));
    }
}
