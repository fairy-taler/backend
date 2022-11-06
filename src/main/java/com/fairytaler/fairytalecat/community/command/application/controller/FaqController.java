package com.fairytaler.fairytalecat.community.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.application.dto.FaqRequestDTO;
import com.fairytaler.fairytalecat.community.command.application.service.FaqService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaqController {
    static private FaqService faqService;

    public FaqController(FaqService faqService){
        this.faqService = faqService;
    }
    @PostMapping("/faq")
    public ResponseEntity<ResponseDTO> insertFaq(@RequestBody FaqRequestDTO faqRequestDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 입력 성공", faqService.insertFaq(faqRequestDTO)));

    }

}
