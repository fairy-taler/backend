package com.fairytaler.fairytalecat.member.domain.repository;

import com.fairytaler.fairytalecat.member.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<Member, String> {

    Member findByMemberCode(Long memberCode);

    Member findByMemberNameAndEmail(String memberName, String email);
}
