package com.fairytaler.fairytalecat.tale.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleInfoRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleVoiceRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fairytaler.fairytalecat.tale.query.dto.TaleTTSRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("")
    public ResponseEntity<ResponseDTO> insertTale(@RequestHeader String accessToken, @ModelAttribute TaleRequestDTO taleRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTale(accessToken,taleRequestDTO)));
    }

    @PostMapping("/tts")
    public ResponseEntity<ResponseDTO> insertTaleTTS(@RequestHeader String accessToken, @RequestBody TaleTTSRequestDTO taleTTSRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTTSTale(accessToken,taleTTSRequestDTO)));
    }

    @PostMapping("/voice")
    public ResponseEntity<ResponseDTO> insertTaleVocie(@RequestHeader String accessToken, @ModelAttribute TaleVoiceRequestDTO taleVoiceRequestDTO) {
        System.out.println(taleVoiceRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTaleVoice(accessToken,taleVoiceRequestDTO)));
    }

    @PostMapping("/info")
    public ResponseEntity<ResponseDTO> insertTaleInfo(@RequestHeader String accessToken, @RequestBody TaleInfoRequestDTO taleInfoRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 세부정보 등록 성공", insertTaleService.insertTaleInfo(accessToken,taleInfoRequestDTO)));
    }

    @PutMapping("/info")
    public ResponseEntity<ResponseDTO> updateTaleInfo(@RequestBody TaleInfoRequestDTO taleInfoRequestDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 세부정보 수정 성공", insertTaleService.updateTaleInfo(taleInfoRequestDTO)));
    }
}