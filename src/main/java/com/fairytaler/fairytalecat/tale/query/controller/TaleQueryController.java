package com.fairytaler.fairytalecat.tale.query.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.service.SearchTaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/tale") // 데이터 리턴 서버
public class TaleQueryController {
    // DI
    private final TaleRepository taleRepository;
    private final InsertTaleService insertTaleService;
    private final SearchTaleService searchTaleService;


    public TaleQueryController(TaleRepository taleRepository, InsertTaleService insertTaleService, SearchTaleService searchTaleService) {
        this.taleRepository = taleRepository;
        this.insertTaleService = insertTaleService;
        this.searchTaleService = searchTaleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> searchTale(@PathVariable String id) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 조회 성공", searchTaleService.searchTaleByTaleCode(id)));
    }

    @GetMapping("/mylist")
    public ResponseEntity<ResponseDTO> searchMyTale(@RequestHeader String accessToken) throws ParseException  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 조회 성공", searchTaleService.searchTaleByMemberCode(accessToken)));
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<ResponseDTO> searchTaleByMemberId(@PathVariable String memberId) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 조회 성공", searchTaleService.searchTaleByMemberId(memberId)));
    }

}