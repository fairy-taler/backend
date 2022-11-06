package com.fairytaler.fairytalecat.community.query.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.query.application.service.FaqQueryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FaqQueryController {

    private static FaqQueryService faqQueryService;

    public FaqQueryController(FaqQueryService faqQueryService){
        this.faqQueryService = faqQueryService;
    }

    /* 상세 조회 */
    @GetMapping("/faq/{faqCode}")
    public ResponseEntity<ResponseDTO> selectFaq(@PathVariable Long faqCode){
        Faq faq =faqQueryService.getFaq(faqCode);
        if(faq == null) {
            return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 조회 실패",faqCode ));
        }
        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 조회 성공",faq));
    }

    /* 전체 조회 */
    @GetMapping("/faq")
    public ResponseEntity<ResponseDTO> selectFaqListWithPaging(Pageable pageable){
        /* role 확인 후, 관리자면 전체 조회, 일반 유저면 public 데이터만 조회*/

        /* 공지사항이 있으면 조회 후 반환 */
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "FAQ 전체 조회 성공", faqQueryService.getNoticeListWidthPaging(pageable)));
    }
}
