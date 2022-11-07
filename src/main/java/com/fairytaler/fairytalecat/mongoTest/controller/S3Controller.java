package com.fairytaler.fairytalecat.mongoTest.controller;


import com.amazonaws.util.IOUtils;
import com.fairytaler.fairytalecat.mongoTest.service.AwsS3Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
public class S3Controller {
    static private AwsS3Service awsS3Service;

    public S3Controller(AwsS3Service awsS3Service){
        this.awsS3Service = awsS3Service;
    }
    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("category") String category,
            @RequestPart(value = "file") MultipartFile multipartFile) {
        return awsS3Service.uploadFileV1(category, multipartFile);
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @RequestParam("resourcePath") String resourcePath) {
        byte[] data = awsS3Service.downloadFileV1(resourcePath);
        ByteArrayResource resource = new ByteArrayResource(data);
        HttpHeaders headers = buildHeaders(resourcePath, data);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(resource);
    }

    private HttpHeaders buildHeaders(String resourcePath, byte[] data) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(data.length);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(resourcePath, StandardCharsets.UTF_8)
                .build());
        return headers;
    }
}