package com.fairytaler.fairytalecat.community.query.application.service;

import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.query.application.dao.FaqQueryDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaqQueryService {
    static private FaqQueryDAO faqDao;

    public FaqQueryService(FaqQueryDAO faqDao){
        this.faqDao=faqDao;
    }
    public Faq getFaq(Long faqCode){
        Optional<Faq> oFaq = faqDao.findByFaqCode(faqCode);
        try{
            return oFaq.get();
        }
        catch (Exception exception){
            return null;
        }
    }
}
