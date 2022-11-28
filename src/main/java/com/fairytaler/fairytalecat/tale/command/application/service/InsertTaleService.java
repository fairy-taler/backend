package com.fairytaler.fairytalecat.tale.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.common.file.AwsS3InsertService;
import com.fairytaler.fairytalecat.community.domain.model.Comment;
import com.fairytaler.fairytalecat.community.domain.model.Faq;
import com.fairytaler.fairytalecat.community.domain.model.Notice;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.mongoTest.model.MongoDBTestModel;
import com.fairytaler.fairytalecat.mongoTest.service.AwsS3Service;
import com.fairytaler.fairytalecat.tale.domain.model.TTSTalePage;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TaleInfo;
import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleInfoRepository;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import com.fairytaler.fairytalecat.tts.application.TTSService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class InsertTaleService {

    private TokenProvider tokenProvider;
    static private TaleRepository taleRepository;
    private TTSService ttsService;
    private AwsS3InsertService awsS3InsertService;
    private TaleInfoRepository taleInfoRepository;

    public InsertTaleService(TokenProvider tokenProvider, TTSService ttsService, AwsS3InsertService awsS3InsertService, TaleInfoRepository taleInfoRepository, TaleRepository taleRepository) {
        this.tokenProvider = tokenProvider;
        this.ttsService = ttsService;
        this.awsS3InsertService = awsS3InsertService;
        this.taleInfoRepository = taleInfoRepository;
        this.taleRepository = taleRepository;
    }

    public Object insertTale(String accessToken, TaleRequestDTO taleRequestDTO) {

        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleRequestDTO);

        /* 엔티티 생성 */
        Tale tale = new Tale();

        /* 동화 데이터 엔티티에 넣기 */
        List<TalePage> pages = new LinkedList<>();

        for(TalePageRequestDTO talePage : taleRequestDTO.getPages()){
            String url = "";
            String url2 = "";
            /* ttsText에 값이 들어온다면 */
            if(!(talePage.getTtsText() == "")){
                System.out.println("티티에스");
                System.out.println("[tts] : " + talePage.getTtsText());
                byte[] bytes = ttsService.ResponseTTS(talePage.getTtsText());

                InputStream inputStream = new ByteArrayInputStream(bytes);

                url = awsS3InsertService.uploadFile(inputStream);
            }
            /* 음성 파일이 들어온다면 */
            else if(!(talePage.getVoice().length == 0)){
                System.out.println("보이스");
//                System.out.println("[voice] : " + talePage.getVoice().length);
                byte[] bytes = talePage.getVoice();
                byte[] base64 = Base64.getEncoder().encode(bytes);
                InputStream inputStream = new ByteArrayInputStream(base64);

                url = awsS3InsertService.uploadFileWithMetaData(inputStream,bytes);
        }
        InputStream inputStream2 = new ByteArrayInputStream(talePage.getRawImg());
        url2 = awsS3InsertService.uploadImage(inputStream2);
            TalePage page = new TalePage(talePage.getPage(), talePage.getData(), url, url2);
            pages.add(page);
        }

        tale.setPages(pages);
        tale.setTitle(taleRequestDTO.getTitle());
        tale.setCreateAt(new Date());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        String memberCode = tokenProvider.getUserCode(accessToken);
        tale.setMemberCode(memberCode);
        System.out.println(tale);
        taleRepository.save(tale);

        System.out.println("tale = " + tale);
        return tale.getPages();
    }
//    public Object insertTaleVoice(String accessToken, TaleVoiceRequestDTO taleRequestDTO) {
//
//        /* 동화 정보 확인(출력) */
//        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleRequestDTO);
//
//        /* 엔티티 생성 */
//        Tale tale = new Tale();
//
//        /* 동화 데이터 엔티티에 넣기 */
//        tale.setTitle(taleRequestDTO.getTitle());
//        tale.setCreateAt(new Date());
//
//        List<TalePage> pages = new ArrayList<>();
//        /* 동화 페이지 안에 있는 음성 파일 S3에 저장 후, 저장 위치 저장*/
//        for(TaleVoicePage voicePage : taleRequestDTO.getPages()){
//            /* 동화 안에 있는 음성 파일 s3에 저장*/
//            String url = awsS3InsertService.uploadFileByMultipartFile(voicePage.getVoice());
//
//            /* 반환받은 url을 entity에 저장*/
//
//            TalePage page = new TalePage(voicePage.getPage(), voicePage.getData(), url);
//            pages.add(page);
//        }
//        tale.setPages(pages);
//        /* 사용자 정보 (작성자) 가져와서 넣기 */
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        System.out.println("memberCode = " + memberCode);
//        tale.setMemberCode(memberCode);
//        System.out.println("[insertTaleService : Tale entity] \n" + tale);
//
//        /* 테일 */
////        if (mongoDBTestRepository.findByName(name) != null) {
////            log.info("[Service][update] name is already exist!!");
////            mongoDBTestModel.setId(mongoDBTestRepository.findByName(name).getId());
////        } else {
////            log.info("[Service][insert] New name received!!");
////        }
//
//        taleRepository.save(tale);
//
//        return "성공";
//    }
//
//    public Object insertTTSTale(String accessToken, TaleTTSRequestDTO taleTTSRequestDTO) {
//
//        /* 동화 정보 확인(출력) */
//        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleTTSRequestDTO);
//
//        /* 엔티티 생성 */
//        Tale tale = new Tale();
//
//        /* 동화 데이터 엔티티에 넣기 */
//        // tale.setPages(TaleTTSRequestDTO.getPages());
//
//        List<TalePage> pages = new LinkedList<>();
//
//        for(TTSTalePage ttsTalePage : taleTTSRequestDTO.getPages()){
//
//            byte[] bytes = ttsService.ResponseTTS(ttsTalePage.getTtsText());
//
//            MultipartFile multipartFile;
//            InputStream inputStream = new ByteArrayInputStream(bytes);
//
//            String url = awsS3InsertService.uploadFile(inputStream);
//
//            TalePage page = new TalePage(ttsTalePage.getPage(), ttsTalePage.getData(), url);
//            pages.add(page);
//        }
//        tale.setPages(pages);
//        tale.setTitle(taleTTSRequestDTO.getTitle());
//        tale.setCreateAt(new Date());
//
//        /* 사용자 정보 (작성자) 가져와서 넣기 */
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        System.out.println("memberCode = " + memberCode);
//        tale.setMemberCode(memberCode);
//        System.out.println("[insertTaleService : Tale entity] \n" + tale);
//
//        /* 테일 */
////        if (mongoDBTestRepository.findByName(name) != null) {
////            log.info("[Service][update] name is already exist!!");
////            mongoDBTestModel.setId(mongoDBTestRepository.findByName(name).getId());
////        } else {
////            log.info("[Service][insert] New name received!!");
////        }
//
//        taleRepository.save(tale);
//
//        System.out.println("tale = " + tale);
//
//        return "성공";
//    }
    public Object updateTale(String accessToken, TaleRequestDTO taleRequestDTO) {

        System.out.println("[updateTaleService : TaleRequestDTO ] \n" + taleRequestDTO);

        /* 엔티티 생성 */
        Optional<Tale> oTale = taleRepository.findById(taleRequestDTO.getId());

        Tale tale;

        /* 데이터 삽입 */
        try{
            tale = oTale.get();
        }
        catch (Exception exception){
            return null;
        }
        /* 동화 데이터 엔티티에 넣기 */
        List<TalePage> pages = new LinkedList<>();

        for(TalePageRequestDTO talePage : taleRequestDTO.getPages()){
            String url = "";
            String url2 = "";
            /* ttsText에 값이 들어온다면 */
            if(!(talePage.getTtsText() == "")){
                System.out.println("[tts] : " + talePage.getTtsText());
                byte[] bytes = ttsService.ResponseTTS(talePage.getTtsText());

                InputStream inputStream = new ByteArrayInputStream(bytes);

                url = awsS3InsertService.uploadFile(inputStream);
            }
            /* 음성 파일이 들어온다면 */
            else if(!(talePage.getVoice().length == 0)){
                System.out.println("[voice] : " + talePage.getVoice().length);
                InputStream inputStream = new ByteArrayInputStream(talePage.getVoice());

                url = awsS3InsertService.uploadFile(inputStream);
            }
            InputStream inputStream2 = new ByteArrayInputStream(talePage.getRawImg());
            url2 = awsS3InsertService.uploadImage(inputStream2);
            TalePage page = new TalePage(talePage.getPage(), talePage.getData(), url, url2);
            pages.add(page);
        }

        tale.setPages(pages);
        tale.setTitle(taleRequestDTO.getTitle());
        tale.setCreateAt(new Date());

        /* 사용자 정보 (작성자) 가져와서 넣기 */
        String memberCode = tokenProvider.getUserCode(accessToken);
        tale.setMemberCode(memberCode);
        System.out.println(tale);
        taleRepository.save(tale);

        System.out.println("tale = " + tale);

        return "성공";
    }
    @Transactional
    public TaleInfo insertTaleInfo(String accessToken, TaleInfoRequestDTO taleInfoRequestDTO) {

        TaleInfo taleInfo = new TaleInfo();

        taleInfo.setId(taleInfoRequestDTO.getId());
        taleInfo.setFontStyle(taleInfoRequestDTO.getFontStyle());
        taleInfo.setFontSize(taleInfoRequestDTO.getFontSize());
        taleInfo.setFontColor(taleInfoRequestDTO.getFontColor());
        taleInfo.setFontPositionX(taleInfoRequestDTO.getFontPositionX());
        taleInfo.setFontPositionY(taleInfoRequestDTO.getFontPositionY());
        taleInfo.setCoverColor(taleInfoRequestDTO.getCoverColor());
        taleInfo.setSticker(taleInfoRequestDTO.getSticker());
        taleInfo.setStickerPositionX(taleInfoRequestDTO.getStickerPositionX());
        taleInfo.setStickerPositionY(taleInfoRequestDTO.getStickerPositionY());

        InputStream inputStream = new ByteArrayInputStream(taleInfoRequestDTO.getInputImg());
        String url = awsS3InsertService.uploadImage(inputStream);

        taleInfo.setThumbNail(url);

        taleInfoRepository.save(taleInfo);

        return taleInfo;
    }

    public TaleInfo updateTaleInfo(TaleInfoRequestDTO taleInfoRequestDTO) {

        Optional<TaleInfo> optionalTaleInfo = taleInfoRepository.findById(taleInfoRequestDTO.getId());
        try{
            TaleInfo taleInfo = optionalTaleInfo.get();

            taleInfo.setFontStyle(taleInfoRequestDTO.getFontStyle());
            taleInfo.setFontSize(taleInfoRequestDTO.getFontSize());
            taleInfo.setFontColor(taleInfoRequestDTO.getFontColor());
            taleInfo.setFontPositionX(taleInfoRequestDTO.getFontPositionX());
            taleInfo.setFontPositionY(taleInfoRequestDTO.getFontPositionY());
            taleInfo.setCoverColor(taleInfoRequestDTO.getCoverColor());
            taleInfo.setSticker(taleInfoRequestDTO.getSticker());
            taleInfo.setStickerPositionX(taleInfoRequestDTO.getStickerPositionX());
            taleInfo.setStickerPositionY(taleInfoRequestDTO.getStickerPositionY());

            InputStream inputStream = new ByteArrayInputStream(taleInfoRequestDTO.getInputImg());
            String url = awsS3InsertService.uploadImage(inputStream);

            taleInfo.setThumbNail(url);

            taleInfoRepository.save(taleInfo);

            return taleInfo;
        }
        catch (Exception exception){
            return null;
        }

    }

    public String deleteTaleByTaleCode(String taleCode){
        Optional<Tale> oTale= taleRepository.findById(taleCode);
        if(oTale.isPresent()) {
            taleRepository.delete(oTale.get());
            return taleCode;
        }
        return null;
    }
    public Tale updateBlockTale(String id, String isBlock) {

        Optional<Tale> optionalTale = taleRepository.findById(id);
        try{
            Tale tale = optionalTale.get();
            tale.setBlock(isBlock);
            taleRepository.save(tale);
            return tale;
        }
        catch (Exception exception){
            return null;
        }
    }
}