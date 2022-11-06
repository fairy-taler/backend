package com.fairytaler.fairytalecat.community.command.application.service;

import com.fairytaler.fairytalecat.community.command.application.dao.FaqDAO;
import com.fairytaler.fairytalecat.community.command.application.dto.FaqRequestDTO;
import com.fairytaler.fairytalecat.community.command.domain.model.Faq;
import com.fairytaler.fairytalecat.community.command.domain.model.Notice;
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

}
