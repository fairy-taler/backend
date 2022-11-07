package com.fairytaler.fairytalecat.member.query.apllication.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.query.apllication.service.MemberService;
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

    @GetMapping()
    public ResponseEntity<ResponseDTO> findMemberById(@RequestHeader String accessToken){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberService.findMemberById(accessToken)));
    }

    @GetMapping("/optional-info")
    public ResponseEntity<ResponseDTO> findOptionalInfo(@RequestHeader String accessToken){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberService.findOptionalInfo(accessToken)));
    }

    @GetMapping("/all-info")
    public ResponseEntity<ResponseDTO> findAllInfo(@RequestHeader String accessToken){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberService.findAllInfo(accessToken)));
    }

}
