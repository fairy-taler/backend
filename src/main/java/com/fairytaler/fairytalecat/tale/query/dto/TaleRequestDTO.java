package com.fairytaler.fairytalecat.tale.query.dto;

import com.fairytaler.fairytalecat.tale.domain.model.TalePage;

import java.util.List;

public class TaleRequestDTO {
    private String title;
    private String createAt;
    private List<TalePage> pages;

    public TaleRequestDTO() {}

    public TaleRequestDTO(String title, String createAt, List<TalePage> pages) {
        this.title = title;
        this.createAt = createAt;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public List<TalePage> getPages() {
        return pages;
    }

    public void setPages(List<TalePage> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "TaleRequestDTO{" +
                "title='" + title + '\'' +
                ", createAt='" + createAt + '\'' +
                ", pages=" + pages +
                '}';
    }
}
