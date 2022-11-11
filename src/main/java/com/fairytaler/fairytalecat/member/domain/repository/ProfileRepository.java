package com.fairytaler.fairytalecat.member.domain.repository;

import com.fairytaler.fairytalecat.member.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    Profile findByMemberCode(Long memberCode);

}
