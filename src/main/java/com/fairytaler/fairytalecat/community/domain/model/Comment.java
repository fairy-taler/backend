package com.fairytaler.fairytalecat.community.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "SEQ_COMMENT_CODE"
        , sequenceName = "SEQ_COMMENT_CODE"
        , initialValue = 1
        , allocationSize = 1
)
@Table(name = "TB_COMMENT")
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_COMMENT_CODE"
    )@Column(name = "COMMENT_CODE")
    private Long commentCode;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "MEMBER_CODE")
    private String memberCode;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "FORUM_CODE")
    private Long forumCode;

    public Comment(){}

}
