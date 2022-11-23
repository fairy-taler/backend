package com.fairytaler.fairytalecat.common.file;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3InsertService{

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFile( InputStream inputStream) {

//        validateFileExists(multipartFile); 파일이 비어있는지 확인
        String fileName = UUID.randomUUID().toString().replace("-", "");

//        String fileName = CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());
        /* 파일 메타 데이타 생성 */
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("audio/mpeg");

        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));


        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    public String uploadImage( InputStream inputStream) {

//        validateFileExists(multipartFile); 파일이 비어있는지 확인
        String fileName = UUID.randomUUID().toString().replace("-", "");

//        String fileName = CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());
        /* 파일 메타 데이타 생성 */
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpeg");

        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));


        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    public String uploadFileByMultipartFile(MultipartFile multipartFile) {

//        validateFileExists(multipartFile); 파일이 비어있는지 확인
        String fileName = UUID.randomUUID().toString().replace("-", "");

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


}