package com.fairytaler.fairytalecat.tale.command.application.service;

import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class InsertTaleService {
    public Object insertTale(String accessToken, TaleRequestDTO taleRequestDTO) {
        return "성공";
    }
}
