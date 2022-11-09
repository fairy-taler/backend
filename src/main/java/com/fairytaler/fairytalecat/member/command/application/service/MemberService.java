package com.fairytaler.fairytalecat.member.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.exception.LoginFailedException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.model.MemberPwd;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberPwdRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestMemberInfoDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestUpdatePwdDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final MemberPwdRepository memberPwdRepository;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, MemberInfoRepository memberInfoRepository, MemberPwdRepository memberPwdRepository) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.memberInfoRepository = memberInfoRepository;
        this.memberPwdRepository = memberPwdRepository;
    }

    public Member updateMemberInfo(String accessToken, RequestMemberInfoDTO requestMemberInfoDTO) {

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        Optional<Member> optionalMember = Optional.of(memberInfoRepository.findByMemberCode(memberCode));

        try{
            Member member = optionalMember.get();
            member.setMemberName(requestMemberInfoDTO.getMemberName());
            member.setNickname(requestMemberInfoDTO.getNickname());
            member.setPhone(requestMemberInfoDTO.getPhone());

            memberInfoRepository.save(member);
            return member;
        }
        catch (Exception exception){
            return null;
        }

    }

    public String updatePwd(String accessToken, RequestUpdatePwdDTO requestUpdatePwdDTO) {
        String memberId = tokenProvider.getUserId(accessToken);
        Optional<MemberDTO> member = memberMapper.findByMemberId(memberId);
        if(!passwordEncoder.matches(requestUpdatePwdDTO.getOriginalPwd(),member.get().getMemberPwd())){
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }
        Optional<MemberPwd> optionalMember = memberPwdRepository.findById(memberId);

        try{
            MemberPwd updateMember = optionalMember.get();
            updateMember.setMemberPwd(passwordEncoder.encode(requestUpdatePwdDTO.getNewPwd()));
            memberPwdRepository.save(updateMember);
        }
        catch (Exception exception){
            return null;
        }

        return memberId;
    }
}
