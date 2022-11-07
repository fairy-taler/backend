package com.fairytaler.fairytalecat.tale.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.mongoTest.model.MongoDBTestModel;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InsertTaleService {

    private TokenProvider tokenProvider;
    static private TaleRepository taleRepository;

    public InsertTaleService (TokenProvider tokenProvider, TaleRepository taleRepository) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
    }

    public Object insertTale(String accessToken, TaleRequestDTO taleRequestDTO) {

        /* 동화 정보 확인(출력) */
        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleRequestDTO);

        /* 엔티티 생성 */
        Tale tale = new Tale();

        /* 동화 데이터 엔티티에 넣기 */
        tale.setPages(taleRequestDTO.getPages());
        tale.setTitle(taleRequestDTO.getTitle());
        tale.setCreateAt(new Date());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        String memberCode = tokenProvider.getUserCode(accessToken);
        System.out.println("memberCode = " + memberCode);
        tale.setMemberCode(memberCode);
        System.out.println("[insertTaleService : Tale entity] \n" + tale);

        /* 테일 */
//        if (mongoDBTestRepository.findByName(name) != null) {
//            log.info("[Service][update] name is already exist!!");
//            mongoDBTestModel.setId(mongoDBTestRepository.findByName(name).getId());
//        } else {
//            log.info("[Service][insert] New name received!!");
//        }

        taleRepository.save(tale);

        return "성공";
    }
}