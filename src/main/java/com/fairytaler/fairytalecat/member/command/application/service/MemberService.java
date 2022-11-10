package com.fairytaler.fairytalecat.member.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.model.Profile;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.domain.repository.ProfileRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestMemberInfoDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestProfileDTO;
import com.fairytaler.fairytalecat.tale.command.application.service.AwsS3InsertService;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final ProfileRepository profileRepository;
    private final AwsS3InsertService awsS3InsertService;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, MemberInfoRepository memberInfoRepository, ProfileRepository profileRepository, AwsS3InsertService awsS3InsertService) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.memberInfoRepository = memberInfoRepository;
        this.profileRepository = profileRepository;
        this.awsS3InsertService = awsS3InsertService;
    }

    public Member updateMemberInfo(String accessToken, RequestMemberInfoDTO requestMemberInfoDTO) {

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        Optional<Member> optionalMember = Optional.of(memberInfoRepository.findByMemberCode(memberCode));

        try{
            Member member = optionalMember.get();
            member.setMemberName(requestMemberInfoDTO.getMemberName());
            member.setNickname(requestMemberInfoDTO.getNickname());
            member.setEmail(requestMemberInfoDTO.getEmail());
            member.setPhone(requestMemberInfoDTO.getPhone());

            memberInfoRepository.save(member);
            return member;
        }
        catch (Exception exception){
            return null;
        }

    }

    public Profile updateProfile(String accessToken, RequestProfileDTO requestProfileDTO) {

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));

        Profile optionalProfile = profileRepository.findByMemberCode(memberCode);

        Profile profile = new Profile();
        if( optionalProfile != null ){
            profile = optionalProfile;
        }

        profile.setMemberCode(memberCode);
        profile.setIntro(requestProfileDTO.getIntro());
        String url = awsS3InsertService.uploadFileByMultipartFile(requestProfileDTO.getProfileImg());
        profile.setImgUrl(url);
        profileRepository.save(profile);
        System.out.println("profile = " + profile);
        return profile;

    }
}
