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
@Table(name="TB_MEMBER")
public class MemberPwd {

    @Id
    @Column(name="MEMBER_ID")
    private String memberId;

    @Column(name="MEMBER_PWD")
    private String memberPwd;

}
