package com.fairytaler.fairytalecat.avatar.command.application.service;

import com.fairytaler.fairytalecat.avatar.domain.model.Avatar;
import com.fairytaler.fairytalecat.avatar.domain.repository.AvatarRepository;
import com.fairytaler.fairytalecat.avatar.query.dto.AvatarRequestDTO;
import com.fairytaler.fairytalecat.exception.AvatarException;
import com.fairytaler.fairytalecat.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAvatarService {

    private AvatarRepository avatarRepository;
    private TokenProvider tokenProvider;

    @Autowired
    public UpdateAvatarService(TokenProvider tokenProvider, AvatarRepository avatarRepository){
        this.tokenProvider = tokenProvider;
        this.avatarRepository = avatarRepository;
    }

    @Transactional
    public Long UpdateAvatar(String accessToken, AvatarRequestDTO avatarRequestDTO){

        Long memberCode = Long.parseLong(tokenProvider.getUserCode(accessToken));
        Avatar originAvatar = avatarRepository.findByMemberCode(memberCode);
        Avatar avatar = originAvatar;
        try {
            if (originAvatar == null) {
                avatar = new Avatar();
                avatar.setMemberCode(memberCode);
            }
            avatar.setAnimal(avatarRequestDTO.getAnimal());
            avatar.setMaterial(avatarRequestDTO.getMaterial());
            avatar.setObjectName(avatarRequestDTO.getObjectName());
            avatarRepository.save(avatar);

            return avatar.getMemberCode();
        }catch (NullPointerException exception){
            exception.printStackTrace();
            throw new AvatarException("아바타 업데이트에 실패 하였습니다");
        }

    }


    public class CommonUtils {
        public void saveIfNullId(Long id, JpaRepository repository, Object entity) {
            if(id == null) {
                repository.save(entity);
            }
        }
    }
}
