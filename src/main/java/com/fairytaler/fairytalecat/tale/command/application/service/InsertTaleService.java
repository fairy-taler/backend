package com.fairytaler.fairytalecat.tale.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.mongoTest.model.MongoDBTestModel;
import com.fairytaler.fairytalecat.mongoTest.service.AwsS3Service;
import com.fairytaler.fairytalecat.tale.domain.model.TTSTalePage;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleTTSRequestDTO;
import com.fairytaler.fairytalecat.tts.application.TTSService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class InsertTaleService {

    private TokenProvider tokenProvider;
    static private TaleRepository taleRepository;
    private TTSService ttsService;
    private AwsS3InsertService awsS3InsertService;

    public InsertTaleService (TokenProvider tokenProvider, TaleRepository taleRepository, TTSService ttsService, AwsS3InsertService awsS3InsertService) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
        this.ttsService = ttsService;
        this.awsS3InsertService = awsS3InsertService;
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

    public Object insertTTSTale(String accessToken, TaleTTSRequestDTO taleTTSRequestDTO) {

        /* 동화 정보 확인(출력) */
        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleTTSRequestDTO);

        /* 엔티티 생성 */
        Tale tale = new Tale();

        /* 동화 데이터 엔티티에 넣기 */
        // tale.setPages(TaleTTSRequestDTO.getPages());

        List<TalePage> pages = new LinkedList<>();

        for(TTSTalePage ttsTalePage : taleTTSRequestDTO.getPages()){

            byte[] bytes = ttsService.ResponseTTS(ttsTalePage.getTtsText());

            MultipartFile multipartFile;
            InputStream inputStream = new ByteArrayInputStream(bytes);

            String url = awsS3InsertService.uploadFile(inputStream);

            TalePage page = new TalePage(ttsTalePage.getPage(), ttsTalePage.getData(), url);
            pages.add(page);
        }
        tale.setPages(pages);
        tale.setTitle(taleTTSRequestDTO.getTitle());
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

        System.out.println("tale = " + tale);

        return "성공";
    }
}