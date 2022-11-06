package com.fairytaler.fairytalecat.community.command.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "SEQ_FAQ_CODE"
        , sequenceName = "SEQ_FAQ_CODE"
        , initialValue = 1
        , allocationSize = 1
)
@Table(name = "TB_FAQ")
public class Faq {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_FAQ_CODE"
    )
    @Column(name="FAQ_CODE")
    private Long faqCode;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="ANSWER")
    private String answer;

    @Column(name="CREATE_DATE")
    private Date createDate;

    @Column(name="IS_PUBLIC")
    private boolean isPublic;

    public Faq(){}

    public Long getFaqCode() {
        return faqCode;
    }

    public void setFaqCode(Long faqCode) {
        this.faqCode = faqCode;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        return "Faq{" +
                "faqCode=" + faqCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", createDate=" + createDate +
                ", isPublic=" + isPublic +
                '}';
    }
}
