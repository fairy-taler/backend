package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.FaqRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.NoticeRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.FaqService;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaqController {
    static private FaqService faqService;

    public FaqController(FaqService faqService){
        this.faqService = faqService;
    }
    /* FAQ 입력 */
    @PostMapping("/faq")
    public ResponseEntity<ResponseDTO> insertFaq(@RequestBody FaqRequestDTO faqRequestDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 입력 성공", faqService.insertFaq(faqRequestDTO)));

    }
    /* FAQ 내용 수정 */
    @PutMapping("/faq")
    public ResponseEntity<ResponseDTO> updateFaq(@RequestBody FaqRequestDTO faqRequestDTO){
        Faq faq = faqService.updateFaq(faqRequestDTO);
        if(faq == null) {
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,  faqRequestDTO.getFaqCode() + "번 FAQ가 존재하지 않음", faqRequestDTO.getFaqCode()));
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 수정 성공", faq));
    }
}
