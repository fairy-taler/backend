package com.fairytaler.fairytalecat.member.service;

import com.fairytaler.fairytalecat.exception.DuplicatedMemberIdException;
import com.fairytaler.fairytalecat.exception.FullInputMemberInfoException;
import com.fairytaler.fairytalecat.exception.LoginFailedException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.dto.TokenDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public MemberDTO join(MemberDTO memberDTO) {

        if(memberDTO.getMemberId() == "" || memberDTO.getMemberPwd() == "" || memberDTO.getMemberName() == "" || memberDTO.getEmail() == "" || memberDTO.getPhone() == ""){
            throw new FullInputMemberInfoException("필수 정보를 모두 입력해주세요");
        }

        if(memberMapper.findById(memberDTO.getMemberId()) != null ){
            throw new DuplicatedMemberIdException("이미 가입된 아이디입니다!");
        }

        if(memberMapper.findByEmail(memberDTO.getEmail()) != null ){
            throw new DuplicatedMemberIdException("이미 가입된 이메일입니다!");
        }

        memberDTO.setMemberPwd(passwordEncoder.encode(memberDTO.getMemberPwd()));

        int result = memberMapper.insertMember(memberDTO);
        return memberDTO;
    }

    @Transactional
    public TokenDTO login(MemberDTO memberDTO) {
        MemberDTO member = memberMapper.findByMemberId(memberDTO.getMemberId())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디 또는 비밀번호 입니다."));

        System.out.println("member = " + member);
        if(!passwordEncoder.matches(memberDTO.getMemberPwd(), member.getMemberPwd())){
            throw new LoginFailedException("잘못된 아이디 또는 비밀번호 입니다.");
        }

        TokenDTO token = tokenProvider.generateTokenDTO(member);

        return token;
    }

}
