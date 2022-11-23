package com.fairytaler.fairytalecat.tale.query.service;

import com.fairytaler.fairytalecat.exception.TaleException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.member.domain.repository.MemberRepository;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
import com.fairytaler.fairytalecat.tale.domain.model.TaleInfo;
import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleInfoRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleListRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleInfoRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SearchTaleService {

    private TokenProvider tokenProvider;
    private TaleRepository taleRepository;
    private TaleListRepository taleListRepository;
    private TaleInfoRepository taleInfoRepository;
    private InsertTaleService insertTaleService;
    private MemberInfoRepository memberInfoRepository;

    public SearchTaleService(TokenProvider tokenProvider, TaleRepository taleRepository, TaleListRepository taleListRepository, TaleInfoRepository taleInfoRepository, InsertTaleService insertTaleService, MemberInfoRepository memberInfoRepository) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
        this.taleListRepository = taleListRepository;
        this.taleInfoRepository = taleInfoRepository;
        this.insertTaleService = insertTaleService;
        this.memberInfoRepository = memberInfoRepository;
    }

    public Object searchTaleByTaleCode(String id) {

        try {
            if (taleRepository.findById(id) == null) {
                throw new TaleException("동화가 존재하지 않습니다!");
            } else {
                System.out.println("taleRepository.findByMemberCode(id); = " + taleRepository.findById(id));
                return taleRepository.findById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TaleException("동화 조회에 실패하였습니다. ");
        }

    }

    public Object searchTaleByMemberCode(String accessToken) {

        String memberCode = tokenProvider.getUserCode(accessToken);
        List<TaleResponseDTO> taleResponseDTOs = new LinkedList<>();

        if (taleListRepository.findByMemberCode(memberCode) == null) {
            throw new TaleException("동화가 존재하지 않습니다!");
        } else {
            System.out.println("taleRepository.findByMemberCode(id); = " + taleListRepository.findByMemberCode(memberCode));
            List<TaleList> taleLists = taleListRepository.findByMemberCode(memberCode);

            for(TaleList taleList : taleLists){
                TaleResponseDTO taleResponseDTO =null;
                try {
                    System.out.println("taleList.getId() = " + taleList.getId());
                    System.out.println("taleInfoRepository.findById(taleList.getId()).get() = " + taleInfoRepository.findTaleInfoById(taleList.getId()));
                    if(taleInfoRepository.findTaleInfoById(taleList.getId()) == null){
                        //TaleInfo taleInfo = new TaleInfo(taleList.getId()," "," "," "," "," "," "," "," "," "," ");
                        TaleInfoRequestDTO taleInfoRequestDTO =new TaleInfoRequestDTO(taleList.getId()," "," "," "," "," "," "," "," "," ", new byte[0]);
                        TaleInfo taleInfo = insertTaleService.insertTaleInfo(accessToken, taleInfoRequestDTO);
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfo);
                    }else{
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfoRepository.findTaleInfoById(taleList.getId()));
                    }
                }catch (Exception e){

                }
                taleResponseDTOs.add(taleResponseDTO);
            }
            return taleResponseDTOs;
        }
    }

    public Object searchTaleByMemberId(String memberId) {

        List<TaleResponseDTO> taleResponseDTOs = new LinkedList<>();

        Member member = memberInfoRepository.findByMemberId(memberId);

        String memberCode = member.getMemberCode().toString();

        if (taleListRepository.findByMemberCode(memberCode) == null) {
            throw new TaleException("동화가 존재하지 않습니다!");
        } else {
            System.out.println("taleRepository.findByMemberCode(id); = " + taleListRepository.findByMemberCode(memberCode));
            List<TaleList> taleLists = taleListRepository.findByMemberCode(memberCode);

            for(TaleList taleList : taleLists){
                TaleResponseDTO taleResponseDTO =null;
                try {
                    if(taleInfoRepository.findTaleInfoById(taleList.getId()) == null){
                        TaleInfoRequestDTO taleInfoRequestDTO =new TaleInfoRequestDTO(taleList.getId()," "," "," "," "," "," "," "," "," ", new byte[0]);
                        TaleInfo taleInfo =  new TaleInfo(taleList.getId(),null,null,null,null,null,null,null,null,null,null);
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfo);
                    }else{
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfoRepository.findTaleInfoById(taleList.getId()));
                    }
                }catch (Exception e){

                }
                taleResponseDTOs.add(taleResponseDTO);
            }
            return taleResponseDTOs;
        }
    }

}
