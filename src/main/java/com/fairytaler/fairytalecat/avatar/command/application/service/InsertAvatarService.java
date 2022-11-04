package com.fairytaler.fairytalecat.avatar.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.avatar.query.dto.AvatarRequestDTO;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertAvatarService {

    private AvatarRepository avatarRepository;
    private TokenProvider tokenProvider;

    @Autowired
    public InsertAvatarService(TokenProvider tokenProvider,  AvatarRepository avatarRepository){
        this.tokenProvider = tokenProvider;
        this.avatarRepository = avatarRepository;
    }

    @Transactional
    public Long InsertAvatar(String accessToken, Long avatarCode){
        System.out.println("avatarCode = " + avatarCode);
        String memberCode = tokenProvider.getUserCode(accessToken);
        System.out.println("memberCode = " + memberCode);
        Avatar avatar = new Avatar(Long.parseLong(memberCode), avatarCode);
        avatarRepository.save(avatar);

        return avatar.getAvatarCode();
    }



}
