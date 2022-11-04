package com.fairytaler.fairytalecat.avatar.domain.repository;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
    Avatar findByMemberCode(Long memberCode);
}
