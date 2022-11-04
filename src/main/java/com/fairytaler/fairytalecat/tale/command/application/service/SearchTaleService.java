package com.fairytaler.fairytalecat.tale.command.application.service;

import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SearchTaleService {

    private TaleRepository taleRepository;

    public SearchTaleService ( TaleRepository taleRepository) {
        this.taleRepository = taleRepository;
    }
    public Object searchTale(String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (taleRepository.findByMemberCode(id) == null) {
                return "동화가 존재하지 않습니다!";
            } else {
                System.out.println("taleRepository.findByMemberCode(id); = " + taleRepository.findByMemberCode(id));
                return taleRepository.findByMemberCode(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }
}
