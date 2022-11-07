package com.fairytaler.fairytalecat.mongoTest.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.fairytaler.fairytalecat.avatar.command.application.service.UpdateAvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
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

//    private void validateFileExists(MultipartFile multipartFile) {
//        if (multipartFile.isEmpty()) {
//            throw new EmptyFileException();
//        }
//    }

    public byte[] downloadFileV1(String resourcePath) {
//        파일이 존재하는지 확인
//        validateFileExistsAtUrl(resourcePath);

        S3Object s3Object = amazonS3Client.getObject(bucketName, resourcePath);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
//            throw new FileDownloadFailedException();
            return null;
        }
    }

//    private void validateFileExistsAtUrl(String resourcePath) {
//        if (!amazonS3Client.doesObjectExist(bucketName, resourcePath)) {
//            throw new FileNotFoundException();
//        }
//    }

    public File test(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return file;
    }
}