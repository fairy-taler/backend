package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.InquiryRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InquiryController {
    static private InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    /* 1대1 문의 등록 */
    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDTO> insertInquiry(@RequestHeader String accessToken, @RequestBody InquiryRequestDTO inquiryRequestDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "1대1문의 등록 성공", inquiryService.insertInquiry(accessToken,inquiryRequestDTO)));
    }

    /* 1대 1 문의 답변 등록*/

}
