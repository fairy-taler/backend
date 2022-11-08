package com.fairytaler.fairytalecat.tts.application;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/tts")
public class TTSController {

    private final TTSService ttsService;

    public TTSController(TTSService ttsService) {
        this.ttsService = ttsService;
    }

    @PostMapping(
            value = "/play",
            produces = "audio/wav"
    )
    public byte[] playTTS2(@RequestBody RequestTTSDTO strDto)  {
        return ttsService.ResponseTTS(strDto.getStr());
    }

}
