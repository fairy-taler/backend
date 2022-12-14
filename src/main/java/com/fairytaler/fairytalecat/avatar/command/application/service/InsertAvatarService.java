package com.fairytaler.fairytalecat.avatar.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.avatar.query.dto.AvatarRequestDTO;
import com.fairytaler.fairytalecat.exception.AvatarException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import com.fairytaler.fairytalecat.member.command.application.dto.MemberDTO;
import com.fairytaler.fairytalecat.member.command.application.dto.TokenDTO;
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
        try {
            avatar.setMemberCode(Long.parseLong(memberCode));
            avatar.setAnimal(avatarRequestDTO.getAnimal());
            avatar.setMaterial(avatarRequestDTO.getMaterial());
            avatar.setObjectName(avatarRequestDTO.getObjectName());
            avatarRepository.save(avatar);
        }catch (Exception exception){
            throw new AvatarException("아바타 생성에 실패하였습니다. ");
        }

        return avatar.getMemberCode();
    }

    @Transactional
    public Avatar InitialAvatar(Long memberCode){
        Avatar avatar = new Avatar();
        avatar.setMemberCode(memberCode);
        avatar.setAnimal("0");
        avatar.setMaterial("0");
        String obj = "{ \"bag\": 8,\"fish\": 8,\"hat\": 8,\"glass\": 8}" ;
        obj.replace("'", "''" );
        avatar.setObjectName(obj);

        avatarRepository.save(avatar);
        return avatar;
    }
}
