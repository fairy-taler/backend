package com.fairytaler.fairytalecat.member.query.apllication.dto;

public class RequestSearchPwdDTO {
    private String newPwd;
    private String memberId;
    private String memberName;

    public RequestSearchPwdDTO(){}

    public RequestSearchPwdDTO(String newPwd, String memberId, String memberName) {
        this.newPwd = newPwd;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "RequestSearchPwdDTO{" +
                "newPwd='" + newPwd + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
