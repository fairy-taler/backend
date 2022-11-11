package com.fairytaler.fairytalecat.member.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="TB_PROFILE")
public class Profile {

    @Id
    @Column(name="MEMBER_CODE")
    private Long memberCode;

    @Column(name="IMG_URL")
    private String imgUrl;

    @Column(name="INTRO")
    private String intro;


}
