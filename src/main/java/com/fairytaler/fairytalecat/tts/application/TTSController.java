package com.fairytaler.fairytalecat.tts.application;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tts")
public class TTSController {

    private final TTSService ttsService;

    public TTSController(TTSService ttsService) {
        this.ttsService = ttsService;
    }


    @GetMapping("/play")
    public ResponseEntity<ResponseDTO> playTTS(@RequestBody RequestTTSDTO strDto)  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "음성 변환 성공!", ttsService.ResponseTTS(strDto.getStr())));
    }

}
