package com.fairytaler.fairytalecat.community.command.application.service;

import com.fairytaler.fairytalecat.community.command.application.dao.InquiryDAO;
import com.fairytaler.fairytalecat.community.command.application.dto.InquiryRequestDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.command.domain.model.Inquiry;
import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class InquiryService {
    static private InquiryDAO inquiryDAO;
    static private TokenProvider tokenProvider;

    public InquiryService(InquiryDAO inquiryDAO, TokenProvider tokenProvider){
        this.inquiryDAO = inquiryDAO;
        this.tokenProvider = tokenProvider;
    }
    public Inquiry insertInquiry(String accessToken, InquiryRequestDTO inquiryRequestDTO) {
        /* 데이터 생성 */
        Inquiry inquiry = new Inquiry();
        inquiry.setTitle(inquiryRequestDTO.getTitle());
        inquiry.setContent(inquiryRequestDTO.getContent());
        inquiry.setCreateDate(new Date());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        String memberCode = tokenProvider.getUserCode(accessToken);
        inquiry.setMemberCode(memberCode);
        inquiryDAO.save(inquiry);

        return inquiry;
    }
    public Inquiry insertInquiryAnswer(InquiryRequestDTO inquiryRequestDTO) {

        Optional<Inquiry> oInquiry = inquiryDAO.findById(inquiryRequestDTO.getInquiryCode());

        /* 데이터 수정 */
        try{
            Inquiry inquiry = oInquiry.get();
            inquiry.setAnswer(inquiryRequestDTO.getAnswer());
            inquiry.setAnswerDate(new Date());

            inquiryDAO.save(inquiry);
        return inquiry;
    }
        catch (Exception exception){
            return null;
        }
    }
    public Inquiry updateInquiry(InquiryRequestDTO inquiryRequestDTO){

        Optional<Inquiry> oInquiry = inquiryDAO.findById(inquiryRequestDTO.getInquiryCode());

        /* 데이터 수정 */
        try{
            Inquiry inquiry = oInquiry.get();
            inquiry.setTitle(inquiryRequestDTO.getTitle());
            inquiry.setContent(inquiryRequestDTO.getContent());

            inquiryDAO.save(inquiry);
            return inquiry;
        }
        catch (Exception exception){
            return null;
        }
    }
}
