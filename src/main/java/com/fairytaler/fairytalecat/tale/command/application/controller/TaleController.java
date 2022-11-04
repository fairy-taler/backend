package com.fairytaler.fairytalecat.tale.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
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


    public TaleController(TaleRepository taleRepository, InsertTaleService insertTaleService) {
        this.taleRepository = taleRepository;
        this.insertTaleService = insertTaleService;
    }

    @PostMapping("/insert-tale")
    public ResponseEntity<ResponseDTO> insertTale(@RequestHeader String accessToken, @RequestBody TaleRequestDTO taleRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTale(accessToken,taleRequestDTO)));

    }

}
