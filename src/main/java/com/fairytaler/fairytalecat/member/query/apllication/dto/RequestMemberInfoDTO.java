package com.fairytaler.fairytalecat.member.query.apllication.dto;

public class RequestMemberInfoDTO {

    private String memberName;
    private String phone;
    private String nickname;

    public RequestMemberInfoDTO(){}

    public RequestMemberInfoDTO(String memberName, String phone, String nickname) {
        this.memberName = memberName;
        this.phone = phone;
        this.nickname = nickname;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "ResponseMemberInfoDTO{" +
                "memberName='" + memberName + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
