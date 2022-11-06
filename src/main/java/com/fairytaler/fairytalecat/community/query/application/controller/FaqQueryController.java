package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.query.application.service.FaqQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FaqQueryController {

    private static FaqQueryService faqService;

    public FaqQueryController(FaqQueryService faqService){
        this.faqService = faqService;
    }

    /* 상세 조회 */
    @GetMapping("/faq/{faqCode}")
    public ResponseEntity<ResponseDTO> selectFaq(@PathVariable Long faqCode){
        Faq faq = faqService.getFaq(faqCode);
        if(faq == null) {
            return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 조회 실패",faqCode ));
        }
        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 조회 성공",faq));
    }
}
