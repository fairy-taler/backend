package com.fairytaler.fairytalecat.avatar.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="TB_AVATAR")
public class Avatar {

    @Id
    @Column(name="MEMBER_CODE")
    private Long memberCode;

    @Column(name="ANIMAL")
    private String animal;


    @Column(name="MATERIAL")
    private String material;


    @Column(name="OBJECT_NAME")
    private String objectName;

}
