package com.fairytaler.fairytalecat.member.query.apllication.dto;

public class ResponseMemberInfoDTO {

    private String memberName;
    private String email;
    private String phone;
    private String nickname;

    public ResponseMemberInfoDTO(){}

    public ResponseMemberInfoDTO(String memberName, String email, String phone, String nickname) {
        this.memberName = memberName;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
