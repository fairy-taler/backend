package com.fairytaler.fairytalecat.community.command.domain.model;

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
@ToString
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
    )
    private Long commentCode;
    private String content;
    private String memberCode;
    private Date createDate;
//    private Long forumCode;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference // 순환참조 방지
    @JoinColumn(name = "FORUM_CODE")
    private Forum forum;

    public Comment(){}

}
