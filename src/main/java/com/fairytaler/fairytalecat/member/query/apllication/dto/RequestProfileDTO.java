package com.fairytaler.fairytalecat.member.query.apllication.dto;

import com.fairytaler.fairytalecat.member.domain.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestProfileDTO {

    private MultipartFile profileImg;
    private String intro;

    public RequestProfileDTO(){}

}
