package com.fairytaler.fairytalecat.member.command.application.service;

import com.fairytaler.fairytalecat.avatar.command.application.service.InsertAvatarService;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.query.dto.ResponseMemberDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final AvatarRepository avatarRepository;
    private final InsertAvatarService insertAvatarService;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, InsertAvatarService insertAvatarService) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.avatarRepository = avatarRepository;
        this.insertAvatarService = insertAvatarService;
    }

    public MemberDTO findMemberById(String accessToken) {
        String memberId = tokenProvider.getUserId(accessToken);
        MemberDTO member = memberMapper.findById(memberId);
        return member;
    }

    public ResponseMemberDTO findOptionalInfo(String accessToken) {
        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        ResponseMemberDTO responseMember = new ResponseMemberDTO();
        responseMember.setMember(memberRepository.findByMemberCode(memberCode));
        if(avatarRepository.findByMemberCode(memberCode) == null){
            responseMember.setAvatar(insertAvatarService.InitialAvatar(memberCode));
        }else {
            responseMember.setAvatar(avatarRepository.findByMemberCode(memberCode));
        }
        return responseMember;
    }
}
