package com.fairytaler.fairytalecat.tale.query.service;

import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleListRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchTaleService {

    private TokenProvider tokenProvider;
    private TaleRepository taleRepository;
    private TaleListRepository taleListRepository;

    public SearchTaleService (TokenProvider tokenProvider, TaleRepository taleRepository, TaleListRepository taleListRepository) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
        this.taleListRepository = taleListRepository;
    }

    public Object searchTaleByTaleCode(String id) {

        try {
            if (taleRepository.findById(id) == null) {
                return "동화가 존재하지 않습니다!";
            } else {
                System.out.println("taleRepository.findByMemberCode(id); = " + taleRepository.findById(id));
                return taleRepository.findById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    public Object searchTaleByMemberId(String accessToken) {

        String memberCode = tokenProvider.getUserCode(accessToken);

        try {
            if (taleListRepository.findByMemberCode(memberCode) == null) {
                return "동화가 존재하지 않습니다!";
            } else {
                System.out.println("taleRepository.findByMemberCode(id); = " + taleListRepository.findByMemberCode(memberCode));
                return taleListRepository.findByMemberCode(memberCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
