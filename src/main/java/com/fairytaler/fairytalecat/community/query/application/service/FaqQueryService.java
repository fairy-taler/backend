package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
import com.fairytaler.fairytalecat.community.query.application.dao.FaqQueryDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaqQueryService {
    static private FaqQueryDAO faqQueryDao;

    public FaqQueryService(FaqQueryDAO faqQueryDao){
        this.faqQueryDao=faqQueryDao;
    }
    public Faq getFaq(Long faqCode){
        Optional<Faq> oFaq = faqQueryDao.findByFaqCode(faqCode);
        try{
            return oFaq.get();
        }
        catch (Exception exception){
            return null;
        }
    }

    public Page<Faq> getNoticeListWidthPaging(Pageable pageable){
        Page<Faq> faq = faqQueryDao.findAll(pageable);

        if(faq == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return faq;
    }
}
