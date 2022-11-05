package com.fairytaler.fairytalecat.community.command.application.dto;

public class NoticeRequestDTO {
    private Long noticeCode;
    private String title;
    private String content;

    public NoticeRequestDTO(Long noticeCode, String title, String content) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoticeRequestDTO{" +
                "noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(Long noticeCode) {
        this.noticeCode = noticeCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
