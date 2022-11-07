package com.fairytaler.fairytalecat.member.command.application.dao;

import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberDTO> findByMemberId(String memberId);

    MemberDTO findById(String memberId);

    public MemberDTO findByEmail(String email);

    public int insertMember(MemberDTO memberDTO);

    MemberDTO findOptionalInfo(String memberId);
}
