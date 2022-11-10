package com.fairytaler.fairytalecat.member.query.apllication.dto;

public class RequestUpdatePwdDTO {

    private String originalPwd;
    private String newPwd;

    public RequestUpdatePwdDTO(){}

    public RequestUpdatePwdDTO(String originalPwd, String newPwd) {
        this.originalPwd = originalPwd;
        this.newPwd = newPwd;
    }

    public String getOriginalPwd() {
        return originalPwd;
    }

    public void setOriginalPwd(String originalPwd) {
        this.originalPwd = originalPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    public String toString() {
        return "RequestUpdatePwdDTO{" +
                "originalPwd='" + originalPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
