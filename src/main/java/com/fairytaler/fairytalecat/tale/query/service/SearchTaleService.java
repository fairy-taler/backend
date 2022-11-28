package com.fairytaler.fairytalecat.tale.query.service;

import com.fairytaler.fairytalecat.common.paging.Pagenation;
import com.fairytaler.fairytalecat.exception.TaleException;
import com.fairytaler.fairytalecat.community.domain.model.Notice;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.domain.model.Member;
import com.fairytaler.fairytalecat.member.domain.repository.MemberInfoRepository;
import com.fairytaler.fairytalecat.report.domain.model.Report;
import com.fairytaler.fairytalecat.report.domain.repository.ReportQueryRepository;
import com.fairytaler.fairytalecat.report.domain.repository.ReportRepository;
import com.fairytaler.fairytalecat.tale.command.application.service.InsertTaleService;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TaleInfo;
import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleInfoRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleListRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleListResponseDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleInfoRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ReportQueryRepository reportQueryRepository;

    public SearchTaleService(TokenProvider tokenProvider, TaleRepository taleRepository, TaleListRepository taleListRepository, TaleInfoRepository taleInfoRepository, InsertTaleService insertTaleService, MemberInfoRepository memberInfoRepository, ReportQueryRepository reportQueryRepository) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
        this.taleListRepository = taleListRepository;
        this.taleInfoRepository = taleInfoRepository;
        this.insertTaleService = insertTaleService;
        this.memberInfoRepository = memberInfoRepository;
        this.reportQueryRepository = reportQueryRepository;
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
                    TaleInfo taleInfo = new TaleInfo(taleList.getId(),null,null,null,null,null,null,null,null,null,null, "N");
                    taleResponseDTO = new TaleResponseDTO(taleList, taleInfo);
                }
                taleResponseDTOs.add(taleResponseDTO);
            }
            return taleResponseDTOs;
        }
    }

    public Object getTaleListWithPaging(Pageable pageable) {

        Page<TaleList> tales = taleListRepository.findAll(pageable);

        if(tales == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return tales;
    }

    public Object searchTaleByTitle(String title,Pageable pageable) {

        Page<TaleList> tales = taleListRepository.findByTitleContaining(title, pageable);

        if(tales == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("해당 번호의 공지사항이 없습니다.");
        }
        return tales;
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
                        TaleInfo taleInfo =  new TaleInfo(taleList.getId(),null,null,null,null,null,null,null,null,null,null, "N");
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfo);
                    }else{
                        taleResponseDTO = new TaleResponseDTO(taleList, taleInfoRepository.findTaleInfoById(taleList.getId()));
                    }
                }catch (Exception e){
                    TaleInfo taleInfo = new TaleInfo(taleList.getId(),null,null,null,null,null,null,null,null,null,null, "N");
                    taleResponseDTO = new TaleResponseDTO(taleList, taleInfo);

                }
                taleResponseDTOs.add(taleResponseDTO);
            }
            return taleResponseDTOs;
        }
    }
    public Pagenation<TaleListResponseDTO> getTaleListWidthPaging(Pageable pageable){
        Page<Tale> tales = taleRepository.findAll(pageable);
        List<TaleListResponseDTO> taleBlockResponseDTOList = new ArrayList<>();

        /* 원하는 데이터를 갖고 있는 DTO 리스트 만들기*/
            for(Tale tale : tales.getContent()){
                /* 엔티티에서 데이터 가져오와서 DTO 정보 저장*/
                TaleListResponseDTO taleBlockResponseDTO = new TaleListResponseDTO();
                taleBlockResponseDTO.setId(tale.getId());
                taleBlockResponseDTO.setTitle(tale.getTitle());
                taleBlockResponseDTO.setIsBlock(tale.getBlock());
                taleBlockResponseDTO.setCreateAt(tale.getCreateAt());

            /* 아이디 찾아서 넣기 */
            Member member = memberInfoRepository.findByMemberCode(Long.parseLong(tale.getMemberCode()));
            taleBlockResponseDTO.setWriterId(member.getMemberId());

            /* 신고 정보 찾아서 넣기 */
            List<Report> report = reportQueryRepository.findByTargetTaleCode(tale.getId());
            taleBlockResponseDTO.setReportSize(report.size());

            taleBlockResponseDTOList.add(taleBlockResponseDTO);
        }
        /* 페이지 네이션에 데이터 셋팅*/
        Pagenation<TaleListResponseDTO> pages = new Pagenation<TaleListResponseDTO>();
        pages.setTotalPages(tales.getTotalPages());
        pages.setTotalElement(tales.getTotalElements());
        pages.setSize(tales.getSize());
        pages.setNumber(tales.getNumber());
        pages.setContent(taleBlockResponseDTOList);

        if(tales == null){
//            throw new NoMemberException(); //예외 처리
            System.out.println("동화가 없습니다.");
        }
        return pages;
    }

}
