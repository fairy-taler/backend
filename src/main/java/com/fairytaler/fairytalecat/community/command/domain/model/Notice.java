package com.fairytaler.fairytalecat.community.command.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TBL_NOTICE")
public class Notice {
    @Id
    @Column(name="NOTICE_NUM")
    private long noticeNum;            //내용
    @Column(name="TITLE")
    private String title;              //제목
    @Column(name="CONTENT")
    private String content;            //내용
    @Column(name="CREATE_DATE")
    private Date createDate;           //작성 일자
    @Column(name="IS_PRIVATE")
    private boolean isPrivate;         //비공개 여부

    public Notice(){}

    public long getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(long noticeNum) {
        this.noticeNum = noticeNum;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNum=" + noticeNum +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
