package com.fairytaler.fairytalecat.avatar.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_AVATAR")
public class Avatar {

    @Id
    @Column(name="MEMBER_CODE")
    private Long memberId;

    @Column(name="AVATAR_CODE")
    private Long avatarCode;

    public Avatar() {}

    public Avatar(Long memberId, Long avatarCode) {
        this.memberId = memberId;
        this.avatarCode = avatarCode;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getAvatarCode() {
        return avatarCode;
    }

    public void setAvatarCode(Long avatarCode) {
        this.avatarCode = avatarCode;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "memberId=" + memberId +
                ", avatarCode=" + avatarCode +
                '}';
    }
}
