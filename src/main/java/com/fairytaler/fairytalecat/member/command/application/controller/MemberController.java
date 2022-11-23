package com.fairytaler.fairytalecat.member.command.application.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.command.application.service.MemberService;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestMemberInfoDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestProfileDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestSearchPwdDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestUpdatePwdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateMemberInfo(@RequestHeader String accessToken, @RequestBody RequestMemberInfoDTO requestMemberInfoDTO) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", memberService.updateMemberInfo(accessToken, requestMemberInfoDTO)));
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseDTO> updateProfile(@RequestHeader String accessToken, @ModelAttribute RequestProfileDTO requestProfileDTO) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 프로필 수정 성공", memberService.updateProfile(accessToken, requestProfileDTO)));
    }
    @PutMapping("/update-pwd")
    public ResponseEntity<ResponseDTO> updatePwd(@RequestHeader String accessToken, @RequestBody RequestUpdatePwdDTO requestUpdatePwdDTO) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", memberService.updatePwd(accessToken, requestUpdatePwdDTO)));
    }

    @PutMapping("/block")
    public ResponseEntity<ResponseDTO> blockMember(@RequestHeader String accessToken, @RequestBody String memberCode) throws ParseException {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 차단 성공", memberService.blockMember(accessToken, memberCode)));
    }

    @PutMapping("/unblock")
    public ResponseEntity<ResponseDTO> unblockMember(@RequestHeader String accessToken, @RequestBody String memberCode) throws ParseException{
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 차단 해제 성공", memberService.unblockMember(accessToken, memberCode)));
    }

    @PutMapping("/search-pwd")
    public ResponseEntity<ResponseDTO> searchPwd(@RequestBody RequestSearchPwdDTO requestSearchPwdDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 수정 성공", memberService.searchPwd(requestSearchPwdDTO)));
    }
}
