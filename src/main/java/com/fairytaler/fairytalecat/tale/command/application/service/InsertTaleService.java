package com.fairytaler.fairytalecat.tale.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.mongoTest.model.MongoDBTestModel;
import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TalePage;
import com.fairytaler.fairytalecat.tale.domain.repository.TaleRepository;
import com.fairytaler.fairytalecat.tale.query.dto.TaleRequestDTO;
import com.fairytaler.fairytalecat.tale.query.dto.TaleVoiceRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InsertTaleService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private TokenProvider tokenProvider;
    static private TaleRepository taleRepository;

    public InsertTaleService (TokenProvider tokenProvider, TaleRepository taleRepository, AmazonS3Client amazonS3Client) {
        this.tokenProvider = tokenProvider;
        this.taleRepository = taleRepository;
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadFileV1(String category, MultipartFile multipartFile) {

//        validateFileExists(multipartFile); 파일이 비어있는지 확인
        String fileName = UUID.randomUUID().toString().replace("-", "");

//        String fileName = CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());
        /* 파일 메타 데이타 생성 */
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {

            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
//            throw new FileUploadFailedException();
            System.out.println("에러1");
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();
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

    public Object insertTaleVoice(String accessToken, TaleVoiceRequestDTO taleVoiceRequestDTO) {

        /* 동화 정보 확인(출력) */
        System.out.println("[insertTaleAudioService : VoiceRequestDTO ] \n" + taleVoiceRequestDTO);

        /* 엔티티 생성 */
        Tale tale = new Tale();

        /* 동화 데이터 엔티티에 넣기 */
//        tale.setPages(taleRequestDTO.getPages());
        tale.setTitle(taleVoiceRequestDTO.getTitle());
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