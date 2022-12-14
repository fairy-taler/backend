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
import com.fairytaler.fairytalecat.exception.TaleException;
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

        /* ????????? ?????? */
        Tale tale = new Tale();

        /* ?????? ????????? ???????????? ?????? */
        List<TalePage> pages = new LinkedList<>();

        for(TalePageRequestDTO talePage : taleRequestDTO.getPages()){
            String url = "";
            String url2 = "";
            /* ttsText??? ?????? ??????????????? */
            if(!(talePage.getTtsText() == "")){
                System.out.println("[tts] : " + talePage.getTtsText());
                byte[] bytes = ttsService.ResponseTTS(talePage.getTtsText());

                InputStream inputStream = new ByteArrayInputStream(bytes);

                url = awsS3InsertService.uploadFile(inputStream);
            }
            /* ?????? ????????? ??????????????? */
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

        /* ????????? ?????? (?????????) ???????????? ?????? */
        String memberCode = tokenProvider.getUserCode(accessToken);
        tale.setMemberCode(memberCode);
        System.out.println(tale);
        taleRepository.save(tale);

        System.out.println("tale = " + tale);

        return "??????";
    }
//    public Object insertTaleVoice(String accessToken, TaleVoiceRequestDTO taleRequestDTO) {
//
//        /* ?????? ?????? ??????(??????) */
//        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleRequestDTO);
//
//        /* ????????? ?????? */
//        Tale tale = new Tale();
//
//        /* ?????? ????????? ???????????? ?????? */
//        tale.setTitle(taleRequestDTO.getTitle());
//        tale.setCreateAt(new Date());
//
//        List<TalePage> pages = new ArrayList<>();
//        /* ?????? ????????? ?????? ?????? ?????? ?????? S3??? ?????? ???, ?????? ?????? ??????*/
//        for(TaleVoicePage voicePage : taleRequestDTO.getPages()){
//            /* ?????? ?????? ?????? ?????? ?????? s3??? ??????*/
//            String url = awsS3InsertService.uploadFileByMultipartFile(voicePage.getVoice());
//
//            /* ???????????? url??? entity??? ??????*/
//
//            TalePage page = new TalePage(voicePage.getPage(), voicePage.getData(), url);
//            pages.add(page);
//        }
//        tale.setPages(pages);
//        /* ????????? ?????? (?????????) ???????????? ?????? */
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        System.out.println("memberCode = " + memberCode);
//        tale.setMemberCode(memberCode);
//        System.out.println("[insertTaleService : Tale entity] \n" + tale);
//
//        /* ?????? */
////        if (mongoDBTestRepository.findByName(name) != null) {
////            log.info("[Service][update] name is already exist!!");
////            mongoDBTestModel.setId(mongoDBTestRepository.findByName(name).getId());
////        } else {
////            log.info("[Service][insert] New name received!!");
////        }
//
//        taleRepository.save(tale);
//
//        return "??????";
//    }
//
//    public Object insertTTSTale(String accessToken, TaleTTSRequestDTO taleTTSRequestDTO) {
//
//        /* ?????? ?????? ??????(??????) */
//        System.out.println("[insertTaleService : TaleRequestDTO ] \n" + taleTTSRequestDTO);
//
//        /* ????????? ?????? */
//        Tale tale = new Tale();
//
//        /* ?????? ????????? ???????????? ?????? */
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
//        /* ????????? ?????? (?????????) ???????????? ?????? */
//        String memberCode = tokenProvider.getUserCode(accessToken);
//        System.out.println("memberCode = " + memberCode);
//        tale.setMemberCode(memberCode);
//        System.out.println("[insertTaleService : Tale entity] \n" + tale);
//
//        /* ?????? */
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
//        return "??????";
//    }
    public Object updateTale(String accessToken, TaleRequestDTO taleRequestDTO) {

        System.out.println("[updateTaleService : TaleRequestDTO ] \n" + taleRequestDTO);

        /* ????????? ?????? */
        Optional<Tale> oTale = taleRepository.findById(taleRequestDTO.getId());

        Tale tale;

        /* ????????? ?????? */
        try{
            tale = oTale.get();
        }
        catch (Exception exception){
            return null;
        }
        /* ?????? ????????? ???????????? ?????? */
        List<TalePage> pages = new LinkedList<>();

        for(TalePageRequestDTO talePage : taleRequestDTO.getPages()){
            String url = "";
            String url2 = "";
            /* ttsText??? ?????? ??????????????? */
            if(!(talePage.getTtsText() == "")){
                System.out.println("[tts] : " + talePage.getTtsText());
                byte[] bytes = ttsService.ResponseTTS(talePage.getTtsText());

                InputStream inputStream = new ByteArrayInputStream(bytes);

                url = awsS3InsertService.uploadFile(inputStream);
            }
            /* ?????? ????????? ??????????????? */
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

        /* ????????? ?????? (?????????) ???????????? ?????? */
        String memberCode = tokenProvider.getUserCode(accessToken);
        tale.setMemberCode(memberCode);
        System.out.println(tale);
        taleRepository.save(tale);

        System.out.println("tale = " + tale);

        return "??????";
    }
    @Transactional
    public TaleInfo insertTaleInfo(String accessToken, TaleInfoRequestDTO taleInfoRequestDTO) {

        TaleInfo taleInfo = new TaleInfo();

        if(taleRepository.findById(taleInfoRequestDTO.getId()).isEmpty()){
            throw new TaleException("?????? ????????? ???????????? ????????????. ");
        }
        try {
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
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new TaleException("?????? ?????? ?????? ????????? ?????????????????????.");
        }
    }

    public TaleInfo updateTaleInfo(TaleInfoRequestDTO taleInfoRequestDTO) {

        Optional<TaleInfo> optionalTaleInfo = taleInfoRepository.findById(taleInfoRequestDTO.getId());

        if(taleRepository.findById(taleInfoRequestDTO.getId()).isEmpty()){
            throw new TaleException("?????? ????????? ???????????? ????????????. ");
        }

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
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new TaleException("?????? ?????? ?????? ????????? ?????????????????????.");
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