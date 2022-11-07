package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.InquiryRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.InquiryService;
import com.fairytaler.fairytalecat.community.command.domain.model.Inquiry;
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
    @PutMapping("/inquiry/answer")
    public ResponseEntity<ResponseDTO> insertInquiry(@RequestBody InquiryRequestDTO inquiryRequestDTO){
        Inquiry inquiry =  inquiryService.insertInquiryAnswer(inquiryRequestDTO);
        if(inquiry==null){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "1대1문의 수정 실패",inquiryRequestDTO.getInquiryCode()+"번 문의가 존재하지 않습니다."));
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "1대1문의 수정 성공",inquiry));
    }
}
