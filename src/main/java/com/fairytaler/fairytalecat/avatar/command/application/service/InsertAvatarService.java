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
    public Long InsertAvatar(String accessToken, AvatarRequestDTO avatarRequestDTO){

        String memberCode = tokenProvider.getUserCode(accessToken);
        Avatar avatar = new Avatar();
        avatar.setMemberCode(Long.parseLong(memberCode));
        avatar.setAnimal(avatarRequestDTO.getAnimal());
        avatar.setMaterial(avatarRequestDTO.getMaterial());
        avatar.setObjectName(avatarRequestDTO.getObjectName());
        avatarRepository.save(avatar);

        return avatar.getMemberCode();
    }



}
