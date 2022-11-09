package com.fairytaler.fairytalecat.member.query.apllication.dto;

public class RequestSearchIdDTO {

    private String memberName;
    private String email;

    public RequestSearchIdDTO(){}

    public RequestSearchIdDTO(String memberName, String email) {
        this.memberName = memberName;
        this.email = email;
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

    @Override
    public String toString() {
        return "ResponseMemberInfoDTO{" +
                "memberName='" + memberName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
