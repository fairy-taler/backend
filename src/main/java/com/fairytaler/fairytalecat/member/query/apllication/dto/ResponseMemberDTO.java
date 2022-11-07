package com.fairytaler.fairytalecat.member.query.apllication.dto;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.member.domain.model.OptionalMemberInfo;

public class ResponseMemberDTO {

    private OptionalMemberInfo Member;
    private Avatar avatar;

    public ResponseMemberDTO() {}

    public ResponseMemberDTO(OptionalMemberInfo member, Avatar avatar) {
        Member = member;
        this.avatar = avatar;
    }

    public OptionalMemberInfo getMember() {
        return Member;
    }

    public void setMember(OptionalMemberInfo member) {
        Member = member;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ResponseMemberDTO{" +
                "Member=" + Member +
                ", avatar=" + avatar +
                '}';
    }
}
