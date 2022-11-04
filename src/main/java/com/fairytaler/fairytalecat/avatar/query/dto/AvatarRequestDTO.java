package com.fairytaler.fairytalecat.avatar.query.dto;

public class AvatarRequestDTO {
    private Long avatarCode;

    public AvatarRequestDTO() {}

    public AvatarRequestDTO(Long avatarCode) {
        this.avatarCode = avatarCode;
    }

    public Long getAvatarCode() {
        return avatarCode;
    }

}
