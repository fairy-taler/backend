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
public class Member {

    @Id
    @Column(name="MEMBER_CODE")
    private Long memberCode;

    @Column(name="MEMBER_ID")
    private String memberId;

    @Column(name="MEMBER_NAME")
    private String memberName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;

    @Column(name="NICKNAME")
    private String nickname;

    @Column(name="MEMBER_ROLE")
    private String memberRole;

    @Column(name="BLOCK_STATUS")
    private String blockStatus;

}
