package com.fairytaler.fairytalecat.member.dao;

import com.fairytaler.fairytalecat.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberDTO> findByMemberId(String memberId);

}
