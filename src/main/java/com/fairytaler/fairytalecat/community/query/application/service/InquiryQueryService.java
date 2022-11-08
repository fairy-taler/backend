package com.fairytaler.fairytalecat.community.query.application.service;

import antlr.Token;
import com.fairytaler.fairytalecat.community.command.domain.model.Inquiry;
import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dao.InquiryQueryDAO;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InquiryQueryService {

    static private InquiryQueryDAO inquiryQueryDao;
    static private TokenProvider tokenProvider;

    public InquiryQueryService(InquiryQueryDAO inquiryQueryDao, TokenProvider tokenProvider){
        this.inquiryQueryDao = inquiryQueryDao;
        this.tokenProvider = tokenProvider;
    }


    public Optional<Inquiry> getInquiry(Long inquiryCode){
        /* 공지사항 조회 */
        Optional<Inquiry> inquiry = inquiryQueryDao.findById(inquiryCode);
        if(inquiry == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 문의가 없습니다.");
        }
        return inquiry;
    }

    public Page<Inquiry> getInquiryListWidthPaging(Pageable pageable){
        Page<Inquiry> inquiry = inquiryQueryDao.findAll(pageable);

        if(inquiry == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 문의가 없습니다.");
        }
        return inquiry;
    }
    public Page<Inquiry> getInquiryByMemberCode(String accessToken,Pageable pageable){

        String memberCode = tokenProvider.getUserCode(accessToken);
        System.out.println("[memberCode] : " + memberCode);
        Page<Inquiry> inquiry = inquiryQueryDao.findByMemberCode(memberCode, pageable);

        if(inquiry == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 문의가 없습니다.");
        }
        return inquiry;
    }
}
