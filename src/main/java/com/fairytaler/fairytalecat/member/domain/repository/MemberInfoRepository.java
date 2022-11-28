package com.fairytaler.fairytalecat.member.domain.repository;

import com.fairytaler.fairytalecat.member.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberInfoRepository extends JpaRepository<Member, String>, JpaSpecificationExecutor<Member> {

    Member findByMemberCode(Long memberCode);

    Member findByMemberId(String memberId);

    Member deleteMemberByMemberCode(Long memberCode);

    Member findByMemberNameAndEmail(String memberName, String email);

    Member findByMemberNameAndMemberId(String memberName, String memberId);


}
