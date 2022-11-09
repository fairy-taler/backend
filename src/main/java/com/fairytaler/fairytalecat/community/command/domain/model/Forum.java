package com.fairytaler.fairytalecat.community.command.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(
        name = "SEQ_FORUM_CODE"
        , sequenceName = "SEQ_FORUM_CODE"
        , initialValue = 1
        , allocationSize = 1
)

@Table(name = "TB_FORUM")
public class Forum {
    @Id@GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "SEQ_FORUM_CODE"
    )
    private Long forumCode;
    private String category;
    private String title;
    private String content;
    private String memberCode;
    private Date CreateDate;

    @OneToMany(mappedBy = "forum")
    @JsonManagedReference // 순환참조 방지
    List<Comment> comments = new ArrayList<>();
    public Forum(){}
}
