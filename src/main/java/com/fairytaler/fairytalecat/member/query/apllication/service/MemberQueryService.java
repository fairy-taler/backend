package com.fairytaler.fairytalecat.member.query.apllication.service;

import com.fairytaler.fairytalecat.avatar.command.application.service.InsertAvatarService;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestSearchIdDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.ResponseMemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberQueryService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final AvatarRepository avatarRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final InsertAvatarService insertAvatarService;

    public MemberQueryService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, MemberInfoRepository memberInfoRepository, InsertAvatarService insertAvatarService) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.avatarRepository = avatarRepository;
        this.memberInfoRepository = memberInfoRepository;
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


    public Member findAllInfo(String accessToken) {
        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        Member member = memberInfoRepository.findByMemberCode(memberCode);

        return member;
    }


    public ResponseMemberDTO findMemberByMemberCode(String code) {
        Long memberCode = Long.parseLong(code);
        ResponseMemberDTO responseMember = new ResponseMemberDTO();
        responseMember.setMember(memberRepository.findByMemberCode(memberCode));
        if(avatarRepository.findByMemberCode(memberCode) == null){
            responseMember.setAvatar(insertAvatarService.InitialAvatar(memberCode));
        }else {
            responseMember.setAvatar(avatarRepository.findByMemberCode(memberCode));
        }
        return responseMember;

    public String searchId(RequestSearchIdDTO requestSearchIdDTO) {
        Member member = memberInfoRepository.findByMemberNameAndEmail(requestSearchIdDTO.getMemberName(), requestSearchIdDTO.getEmail());
        return member.getMemberId();
    }

    public List<Member> findAllMember(String accessToken) {
        Authentication auth = tokenProvider.getAuthentication(accessToken);
        if(auth.getAuthorities().toString().equals("[[ADMIN]]")){
            List<Member> members = memberInfoRepository.findAll();
            return members;
        }
        return null;

    }
}
