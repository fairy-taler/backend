package com.fairytaler.fairytalecat.community.query.application.dto;

import java.util.Date;

public class NoticeDTO {
    private int noticeCode;
    private String title;
    private String content;
    private String isPublic;
    private Date craeteDate;

    public NoticeDTO(){}

    public NoticeDTO(int noticeCode, String title, String content, String isPublic, Date craeteDate) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.craeteDate = craeteDate;
    }

    public int getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(int noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public Date getCraeteDate() {
        return craeteDate;
    }

    public void setCraeteDate(Date craeteDate) {
        this.craeteDate = craeteDate;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPublic='" + isPublic + '\'' +
                ", craeteDate=" + craeteDate +
                '}';
    }
}
