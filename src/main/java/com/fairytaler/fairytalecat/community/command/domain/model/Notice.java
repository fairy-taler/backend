package com.fairytaler.fairytalecat.community.command.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "SEQ_NOTICE_CODE"
        , sequenceName = "SEQ_NOTICE_CODE"
        , initialValue = 1
        , allocationSize = 1
)
@Table(name = "TB_NOTICE")
public class Notice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_NOTICE_CODE"
    )
    @Column(name="NOTICE_CODE")
    private long noticeCode;           //내용
    @Column(name="TITLE")
    private String title;              //제목
    @Column(name="CONTENT")
    private String content;            //내용
    @Column(name="CREATE_DATE")
    private Date createDate;           //작성 일자
    @Column(name="IS_PUBLIC")
    private boolean isPublic;          //비공개 여부

    public Notice(){}

    public Notice(long noticeCode, String title, String content, Date createDate, boolean isPublic) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.isPublic = isPublic;
    }

    public long getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(long noticeCode) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", isPublic=" + isPublic +
                '}';
    }
}
