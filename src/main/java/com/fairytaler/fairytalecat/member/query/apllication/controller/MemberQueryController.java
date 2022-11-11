package com.fairytaler.fairytalecat.member.query.apllication.controller;

import com.fairytaler.fairytalecat.common.response.ResponseDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestSearchIdDTO;
import com.fairytaler.fairytalecat.member.query.apllication.service.MemberQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberQueryController {

    private final MemberQueryService memberQueryService;

    public MemberQueryController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> findMemberById(@RequestHeader String accessToken) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberQueryService.findMemberById(accessToken)));
    }

    @GetMapping("/optional-info")
    public ResponseEntity<ResponseDTO> findOptionalInfo(@RequestHeader String accessToken) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberQueryService.findOptionalInfo(accessToken)));
    }

    @GetMapping("/all-info")
    public ResponseEntity<ResponseDTO> findAllInfo(@RequestHeader String accessToken) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberQueryService.findAllInfo(accessToken)));
    }


    @GetMapping("/{memberCode}")
    public ResponseEntity<ResponseDTO> findMemberByMemberCode(@PathVariable String memberCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 정보 조회 성공", memberQueryService.findMemberByMemberCode(memberCode)));
    }
    @PostMapping("/search-id")
    public ResponseEntity<ResponseDTO> searchId (@RequestBody RequestSearchIdDTO requestSearchIdDTO){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 아이디 조회 성공", memberQueryService.searchId(requestSearchIdDTO)));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> findAllMember (@RequestHeader String accessToken){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 회원 조회 성공", memberQueryService.findAllMember(accessToken)));
    }

}