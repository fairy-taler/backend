package com.fairytaler.fairytalecat.member.query.apllication.dto;

import com.fairytaler.fairytalecat.member.domain.model.Profile;

public class ResponseProfileDTO {

    private String memberName;
    private Profile profile;
    private int taleCount;

    public ResponseProfileDTO () {}

    public ResponseProfileDTO(String memberName, Profile profile, int taleCount) {
        this.memberName = memberName;
        this.profile = profile;
        this.taleCount = taleCount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getTaleCount() {
        return taleCount;
    }

    public void setTaleCount(int taleCount) {
        this.taleCount = taleCount;
    }

    @Override
    public String toString() {
        return "ResponseProfileDTO{" +
                "memberName='" + memberName + '\'' +
                ", profile=" + profile +
                ", taleCount=" + taleCount +
                '}';
    }
}
