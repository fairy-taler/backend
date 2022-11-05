package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.Getter;

import java.util.List;

@Getter
public class TalePage {

    private String page;
    private List<String> data;

    public TalePage(String page, List<String> data) {
        this.page = page;
        this.data = data;
    }

    @Override
    public String toString() {
        return "TalePage{" +
                "page='" + page + '\'' +
                ", data=" + data +
                '}';
    }
}
