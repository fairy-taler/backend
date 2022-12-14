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
    public ResponseEntity<ResponseDTO> insertTale(@RequestHeader String accessToken, @RequestBody TaleRequestDTO taleRequestDTO) {
        System.out.println(taleRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTale(accessToken,taleRequestDTO)));
    }
    @PutMapping("")
    public ResponseEntity<ResponseDTO> updateTale(@RequestHeader String accessToken, @RequestBody TaleRequestDTO taleRequestDTO) {
        System.out.println(taleRequestDTO);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.updateTale(accessToken,taleRequestDTO)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> searchTale(@PathVariable String id) {
        String deleteId =  insertTaleService.deleteTaleByTaleCode(id);
        if(deleteId == null){
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 삭제 실패", id + ": 동화가 없습니다."));
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "동화 삭제 성공", deleteId));
    }


//    @PostMapping("/tts")
//    public ResponseEntity<ResponseDTO> insertTaleTTS(@RequestHeader String accessToken, @RequestBody TaleTTSRequestDTO taleTTSRequestDTO) {
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTTSTale(accessToken,taleTTSRequestDTO)));
//    }
//
//    @PostMapping("/voice")
//    public ResponseEntity<ResponseDTO> insertTaleVocie(@RequestHeader String accessToken, @ModelAttribute TaleVoiceRequestDTO taleVoiceRequestDTO) {
//        System.out.println(taleVoiceRequestDTO);
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 등록 성공", insertTaleService.insertTaleVoice(accessToken,taleVoiceRequestDTO)));
//    }

    @PostMapping("/info")
    public ResponseEntity<ResponseDTO> insertTaleInfo(@RequestHeader String accessToken, @RequestBody TaleInfoRequestDTO taleInfoRequestDTO) throws Exception {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 세부정보 등록 성공", insertTaleService.insertTaleInfo(accessToken,taleInfoRequestDTO)));
    }

    @PutMapping("/info")
    public ResponseEntity<ResponseDTO> updateTaleInfo(@RequestBody TaleInfoRequestDTO taleInfoRequestDTO) throws Exception {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 세부정보 수정 성공", insertTaleService.updateTaleInfo(taleInfoRequestDTO)));
    }

    /* 동화 차단 */
    @PutMapping("/block")
    public ResponseEntity<ResponseDTO> updateBlockTale(@RequestParam String id) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 차단 성공", insertTaleService.updateBlockTale(id, "Y")));
    }
    /* 동화 차단 해제*/
    @PutMapping("/unblock")
    public ResponseEntity<ResponseDTO> updateUnBlockTale(@RequestParam String id) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "동화 차단 해제 성공", insertTaleService.updateBlockTale(id, "N")));
    }
}