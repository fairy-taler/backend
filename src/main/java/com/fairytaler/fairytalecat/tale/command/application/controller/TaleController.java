package com.fairytaler.fairytalecat.tale.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
import com.fairytaler.fairytalecat.tale.command.application.service.SearchTaleService;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tale") // 데이터 리턴 서버
public class TaleController {
    // DI
    private final TaleRepository taleRepository;
    private final InsertTaleService insertTaleService;
    private final SearchTaleService searchTaleService;


    public TaleController(TaleRepository taleRepository, InsertTaleService insertTaleService, SearchTaleService searchTaleService) {
        this.taleRepository = taleRepository;
        this.insertTaleService = insertTaleService;
        this.searchTaleService = searchTaleService;
    }

    @PostMapping("/insert-tale")
    public ResponseEntity<ResponseDTO> insertTale(@RequestHeader String accessToken, @RequestBody TaleRequestDTO taleRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTale(accessToken,taleRequestDTO)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> searchTale(@PathVariable String id) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 조회 성공", searchTaleService.searchTaleByTaleCode(id)));
    }

    @GetMapping("/mylist")
    public ResponseEntity<ResponseDTO> searchTaleBymemberId(@RequestHeader String accessToken) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 조회 성공", searchTaleService.searchTaleByMemberId(accessToken)));
    }

}
