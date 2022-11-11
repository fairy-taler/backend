package com.fairytaler.fairytalecat.community.command.application.service;

import com.fairytaler.fairytalecat.community.command.application.dao.FaqDAO;
import com.fairytaler.fairytalecat.community.command.application.dto.FaqRequestDTO;
import com.fairytaler.fairytalecat.community.domain.model.Faq;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class FaqService {
    static private FaqDAO faqDAO;

    public FaqService(FaqDAO faqDao){
        this.faqDAO = faqDao;
    }

    public Faq insertFaq(FaqRequestDTO faqRequestDTO){
        /* 데이터 생성 */
        Faq faq = new Faq();
        faq.setTitle(faqRequestDTO.getTitle());
        faq.setContent(faqRequestDTO.getContent());
        faq.setAnswer(faqRequestDTO.getAnswer());
        faq.setCreateDate(new Date());
        faq.setPublic(true);

        faqDAO.save(faq);

        return faq;
    }
    public Faq updateFaq(FaqRequestDTO faqRequestDTO){
        Optional<Faq> oFaq = faqDAO.findById(faqRequestDTO.getFaqCode());

        /* 데이터 삽입 */
        try{
            Faq faq = oFaq.get();
            faq.setTitle(faqRequestDTO.getTitle());
            faq.setContent(faqRequestDTO.getContent());

            faqDAO.save(faq);
            return faq;
        }
        catch (Exception exception){
            return null;
        }
    }
    public Faq updateFaqToPublic(Long faqCode, boolean isPublic){
        Optional<Faq> oFaq = faqDAO.findById(faqCode);

        /* 데이터 삽입 */
        try{

            Faq faq = oFaq.get();

            faq.setPublic(isPublic);

            faqDAO.save(faq);

            return faq;
        }
        catch (Exception exception){
            return null;
        }
    }
    public Long deleteFaq(Long faqCode){
        Optional<Faq> oFaq = faqDAO.findById(faqCode);
        if(oFaq.isPresent()) {
            faqDAO.delete(oFaq.get());
            return faqCode;
        }
        return null;
    }
}
