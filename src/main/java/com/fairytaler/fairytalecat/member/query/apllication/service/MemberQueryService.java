package com.fairytaler.fairytalecat.member.query.apllication.service;

import com.fairytaler.fairytalecat.avatar.command.application.service.InsertAvatarService;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.exception.AuthorizationException;
import com.fairytaler.fairytalecat.exception.LoginFailedException;
import com.fairytaler.fairytalecat.exception.UserNotFoundException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.model.MemberPwd;
import com.fairytaler.fairytalecat.member.domain.model.Profile;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.domain.repository.ProfileRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.ResponseMemberDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.ResponseProfileDTO;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestSearchIdDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.ResponseMemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberQueryService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final AvatarRepository avatarRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final InsertAvatarService insertAvatarService;
    private final ProfileRepository profileRepository;
    private final TaleRepository taleRepository;

    public MemberQueryService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, MemberInfoRepository memberInfoRepository, InsertAvatarService insertAvatarService, ProfileRepository profileRepository, TaleRepository taleRepository) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.avatarRepository = avatarRepository;
        this.memberInfoRepository = memberInfoRepository;
        this.insertAvatarService = insertAvatarService;
        this.profileRepository = profileRepository;
        this.taleRepository = taleRepository;
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
        if (avatarRepository.findByMemberCode(memberCode) == null) {
            responseMember.setAvatar(insertAvatarService.InitialAvatar(memberCode));
        } else {
            responseMember.setAvatar(avatarRepository.findByMemberCode(memberCode));
        }
        return responseMember;
    }

    public String searchId(RequestSearchIdDTO requestSearchIdDTO) {
        Member member = memberInfoRepository.findByMemberNameAndEmail(requestSearchIdDTO.getMemberName(), requestSearchIdDTO.getEmail());
        if(member == null ){
            throw new LoginFailedException("일치하는 회원 정보가 없습니다.");
        }
        return member.getMemberId();
    }

    public List<Member> findAllMember(String accessToken) {
        Authentication auth = tokenProvider.getAuthentication(accessToken);
        if(auth.getAuthorities().toString().equals("[[ADMIN]]")){
            List<Member> members = memberInfoRepository.findAll();
            return members;
        }else {
            throw new AuthorizationException("접근 권한이 없습니다.");
        }
    }


    public ResponseProfileDTO findProfile(String accessToken) {
        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        ResponseProfileDTO responseProfileDTO = new ResponseProfileDTO();
        Profile profile = profileRepository.findByMemberCode(memberCode);
        Member member = memberInfoRepository.findByMemberCode(memberCode);
        responseProfileDTO.setProfile(profile);
        responseProfileDTO.setMemberName(member.getMemberName());
        responseProfileDTO.setTaleCount(taleRepository.countTaleByMemberCode(memberCode.toString()));

        return responseProfileDTO;
    }

    public ResponseProfileDTO findProfileById(String memberId) {
        ResponseProfileDTO responseProfileDTO = new ResponseProfileDTO();
        Long memberCode = memberInfoRepository.findByMemberId(memberId).getMemberCode();
        Profile profile = profileRepository.findByMemberCode(memberCode);
        Member member = memberInfoRepository.findByMemberCode(memberCode);
        responseProfileDTO.setProfile(profile);
        responseProfileDTO.setMemberName(member.getMemberName());
        responseProfileDTO.setTaleCount(taleRepository.countTaleByMemberCode(memberCode.toString()));

        return responseProfileDTO;
    }
}
