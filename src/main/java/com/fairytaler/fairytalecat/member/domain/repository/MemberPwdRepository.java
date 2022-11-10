package com.fairytaler.fairytalecat.member.domain.repository;

import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.model.MemberPwd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPwdRepository extends JpaRepository<MemberPwd, String> {

}
