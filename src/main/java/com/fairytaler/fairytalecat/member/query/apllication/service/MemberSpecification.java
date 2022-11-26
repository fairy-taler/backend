package com.fairytaler.fairytalecat.member.query.apllication.service;

import com.fairytaler.fairytalecat.member.domain.model.Member;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {

    public static Specification<Member> equalMemberId(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("memberId"),"%" + keyword + "%"));
    }
    public static Specification<Member> equalMemberName(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("memberName"),"%" + keyword + "%"));
    }
    public static Specification<Member> equalNickname(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nickname"),"%" + keyword + "%"));
    }
    public static Specification<Member> equalEmail(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"),"%" + keyword + "%"));
    }
    public static Specification<Member> equalPhone(String keyword){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("phone"),"%" + keyword + "%"));
    }
}
