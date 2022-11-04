package com.fairytaler.fairytalecat.tale.domain.model;

import java.util.List;

public class TalePage {

    private String page;
    private List<String> data;

    public TalePage(String page, List<String> data) {
        this.page = page;
        this.data = data;
    }
}
