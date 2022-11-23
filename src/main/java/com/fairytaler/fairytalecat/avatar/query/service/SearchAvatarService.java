package com.fairytaler.fairytalecat.avatar.query.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.exception.AvatarException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchAvatarService {

    private AvatarRepository avatarRepository;
    private TokenProvider tokenProvider;

    @Autowired
    public SearchAvatarService(TokenProvider tokenProvider,  AvatarRepository avatarRepository){
        this.tokenProvider = tokenProvider;
        this.avatarRepository = avatarRepository;
    }

    public Avatar SearchAvatar(String accessToken) {
        try {
            String memberCode = tokenProvider.getUserCode(accessToken);
            return avatarRepository.findByMemberCode(Long.parseLong(memberCode));
        }catch (Exception exception){
            throw new AvatarException("아바타 정보가 없습니다.");
        }
    }
}
