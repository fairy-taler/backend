package com.fairytaler.fairytalecat.member.domain.repository;

import com.fairytaler.fairytalecat.member.domain.model.OptionalMemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<OptionalMemberInfo, String> {

    OptionalMemberInfo findByMemberCode(Long memberCode);
}
