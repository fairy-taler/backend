package com.fairytaler.fairytalecat.member.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.command.application.service.MemberService;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestMemberInfoDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestUpdatePwdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateMemberInfo(@RequestHeader String accessToken, @RequestBody RequestMemberInfoDTO requestMemberInfoDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", memberService.updateMemberInfo(accessToken, requestMemberInfoDTO)));
    }

    @PutMapping("/update-pwd")
    public ResponseEntity<ResponseDTO> updatePwd(@RequestHeader String accessToken, @RequestBody RequestUpdatePwdDTO requestUpdatePwdDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", memberService.updatePwd(accessToken, requestUpdatePwdDTO)));
    }
}
