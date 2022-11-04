package com.fairytaler.fairytalecat.avatar.command.application.controller;

import com.fairytaler.fairytalecat.avatar.command.application.service.InsertAvatarService;
import com.fairytaler.fairytalecat.avatar.query.dto.AvatarRequestDTO;
import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

    private final InsertAvatarService insertAvatarService;

    public AvatarController(InsertAvatarService insertAvatarService) {
        this.insertAvatarService= insertAvatarService;
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertAvatar(@RequestBody AvatarRequestDTO avatarCode, @RequestHeader String accessToken)  {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "아바타 등록 성공", insertAvatarService.InsertAvatar(accessToken, avatarCode.getAvatarCode())));
    }
}
