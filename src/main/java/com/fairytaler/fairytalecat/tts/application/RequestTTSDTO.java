package com.fairytaler.fairytalecat.tts.application;

public class RequestTTSDTO {

    private String str;

    public RequestTTSDTO(){}

    public RequestTTSDTO(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "RequestTTSDTO{" +
                "str='" + str + '\'' +
                '}';
    }
}
