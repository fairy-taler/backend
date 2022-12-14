package com.fairytaler.fairytalecat.member.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.exception.BlockFailedException;
import com.fairytaler.fairytalecat.exception.ChangePwdFailedException;
import com.fairytaler.fairytalecat.common.file.AwsS3InsertService;
import com.fairytaler.fairytalecat.exception.LoginFailedException;
import com.fairytaler.fairytalecat.exception.UpdateFailedException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dao.MemberMapper;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.domain.model.Member;

import com.fairytaler.fairytalecat.member.domain.model.Profile;
import com.fairytaler.fairytalecat.member.domain.model.MemberPwd;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberPwdRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.member.domain.repository.ProfileRepository;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestMemberInfoDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestProfileDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestSearchPwdDTO;
import com.fairytaler.fairytalecat.member.query.apllication.dto.RequestUpdatePwdDTO;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final ProfileRepository profileRepository;
    private final AwsS3InsertService awsS3InsertService;

    private final MemberPwdRepository memberPwdRepository;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, MemberRepository memberRepository, AvatarRepository avatarRepository, MemberInfoRepository memberInfoRepository, ProfileRepository profileRepository, AwsS3InsertService awsS3InsertService, MemberPwdRepository memberPwdRepository) {

        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.memberRepository = memberRepository;
        this.memberInfoRepository = memberInfoRepository;
        this.profileRepository = profileRepository;
        this.awsS3InsertService = awsS3InsertService;

        this.memberPwdRepository = memberPwdRepository;
    }

    @Transactional
    public Member updateMemberInfo(String accessToken, RequestMemberInfoDTO requestMemberInfoDTO) {

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        Optional<Member> optionalMember = Optional.of(memberInfoRepository.findByMemberCode(memberCode));
        System.out.println("optionalMember = " + optionalMember.get());
        try{
            Member member = optionalMember.get();

            member.setMemberCode(memberCode);
            member.setMemberName(requestMemberInfoDTO.getMemberName());
            member.setNickname(requestMemberInfoDTO.getNickname());
            member.setPhone(requestMemberInfoDTO.getPhone());

            memberInfoRepository.save(member);
            return member;
        } catch (Exception exception){
            exception.printStackTrace();
            throw new UpdateFailedException("??????????????? ?????????????????????. ");
        }

    }

    @Transactional
    public Profile updateProfile(String accessToken, RequestProfileDTO requestProfileDTO) {

        System.out.println("requestProfileDTO.getProfileImg() = " + requestProfileDTO.getProfileImg());

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));

        Profile optionalProfile = profileRepository.findByMemberCode(memberCode);

        Profile profile = new Profile();
        if (optionalProfile != null) {
            profile = optionalProfile;
        }
        try {
            profile.setMemberCode(memberCode);
            System.out.println("requestProfileDTO.getIntro() = " + requestProfileDTO.getIntro());
            profile.setIntro(requestProfileDTO.getIntro());
            if(requestProfileDTO.getProfileImg() != null ){
                String url = awsS3InsertService.uploadFileByMultipartFile(requestProfileDTO.getProfileImg());
                profile.setImgUrl(url);
            }
            profileRepository.save(profile);
            System.out.println("profile = " + profile);
        }catch (Exception exception){
            throw new UpdateFailedException("?????? ????????? ????????? ?????????????????????. ");
        }

        return profile;
    }

    @Transactional
    public String updatePwd(String accessToken, RequestUpdatePwdDTO requestUpdatePwdDTO) {
        String memberId = tokenProvider.getUserId(accessToken);
        Optional<MemberDTO> member = memberMapper.findByMemberId(memberId);
        if(!passwordEncoder.matches(requestUpdatePwdDTO.getOriginalPwd(),member.get().getMemberPwd())){
            throw new ChangePwdFailedException("????????? ???????????? ?????????.");
        }
        Optional<MemberPwd> optionalMember = memberPwdRepository.findById(memberId);

        try{
            MemberPwd updateMember = optionalMember.get();
            updateMember.setMemberPwd(passwordEncoder.encode(requestUpdatePwdDTO.getNewPwd()));
            memberPwdRepository.save(updateMember);
        }
        catch (Exception exception){
            throw new ChangePwdFailedException("???????????? ???????????? ?????????????????????.");
        }

        return memberId;
    }

    @Transactional
    public Object blockMember(String accessToken, String memberCode) {
        Authentication auth = tokenProvider.getAuthentication(accessToken);
        if(auth.getAuthorities().toString().equals("[[ADMIN]]")){
            try {
                Optional<Member> optionalMemberDTO = Optional.ofNullable(memberInfoRepository.findByMemberCode(Long.parseLong(memberCode)));
                Member member = optionalMemberDTO.get();
                member.setBlockStatus("Y");
                return member;
            }catch (Exception exception) {
                throw new BlockFailedException("?????? ????????? ?????????????????????");
            }
        }
        return memberCode;
    }

    @Transactional
    public Object unblockMember(String accessToken, String memberCode) {
        Authentication auth = tokenProvider.getAuthentication(accessToken);
        if(auth.getAuthorities().toString().equals("[[ADMIN]]")){
            try {
                Optional<Member> optionalMemberDTO = Optional.ofNullable(memberInfoRepository.findByMemberCode(Long.parseLong(memberCode)));
                Member member = optionalMemberDTO.get();
                member.setBlockStatus("N");
                return member;
            }catch (Exception exception) {
                throw new BlockFailedException("?????? ?????? ????????? ?????????????????????. ");
            }
        }
        return memberCode;
    }

    public Object searchPwd(RequestSearchPwdDTO requestSearchPwdDTO) {

        Member member = memberInfoRepository.findByMemberNameAndMemberId(requestSearchPwdDTO.getMemberName(), requestSearchPwdDTO.getMemberId());
        if(member == null ){
            throw new LoginFailedException("???????????? ?????? ????????? ????????????.");
        }
        Optional<MemberPwd> optionalMember = memberPwdRepository.findById(member.getMemberId());

        try{
            MemberPwd updateMember = optionalMember.get();
            updateMember.setMemberPwd(passwordEncoder.encode(requestSearchPwdDTO.getNewPwd()));
            memberPwdRepository.save(updateMember);
        }
        catch (Exception exception){
            throw new ChangePwdFailedException("?????? ???????????? ????????? ?????????????????????. ");
        }

        return member.getMemberId();
    }

}
