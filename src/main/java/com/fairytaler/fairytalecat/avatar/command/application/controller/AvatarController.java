package com.fairytaler.fairytalecat.avatar.command.application.controller;

import com.fairytaler.fairytalecat.avatar.command.application.service.InsertAvatarService;
import com.fairytaler.fairytalecat.avatar.command.application.service.SearchAvatarService;
import com.fairytaler.fairytalecat.avatar.command.application.service.UpdateAvatarService;
import com.fairytaler.fairytalecat.avatar.query.dto.AvatarRequestDTO;
import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

    private final InsertAvatarService insertAvatarService;
    private final SearchAvatarService searchAvatarService;
    private final UpdateAvatarService updateAvatarService;

    public AvatarController(InsertAvatarService insertAvatarService, SearchAvatarService searchAvatarService, UpdateAvatarService updateAvatarService) {
        this.insertAvatarService = insertAvatarService;
        this.searchAvatarService = searchAvatarService;
        this.updateAvatarService = updateAvatarService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertAvatar(@RequestBody AvatarRequestDTO avatarRequestDTO, @RequestHeader String accessToken)  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "아바타 등록 성공", insertAvatarService.InsertAvatar(accessToken, avatarRequestDTO)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> avatarSearch(@RequestHeader String accessToken)  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "아바타 조회 성공", searchAvatarService.SearchAvatar(accessToken)));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAvatar(@RequestBody AvatarRequestDTO avatarRequestDTO, @RequestHeader String accessToken)  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "아바타 변경 성공", updateAvatarService.UpdateAvatar(accessToken, avatarRequestDTO)));
    }
}
