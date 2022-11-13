package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.*;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Argument;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name= "TB_TALE_INFO")
public class TaleInfo {

    @Id
    @Column(name="ID")
    private String id;              // 고유 번호

    @Column(name="FONT_STYLE")
    private String fontStyle;

    @Column(name="FONT_SIZE")
    private String fontSize;

    @Column(name="FONT_COLOR")
    private String fontColor;

    @Column(name="FONT_POSITION_X")
    private String fontPositionX;

    @Column(name="FONT_POSITION_Y")
    private String fontPositionY;

    @Column(name="COVER_COLOR")
    private String coverColor;

    @Column(name="STICKER")
    private String sticker;

    @Column(name="STICKER_POSITION_X")
    private String stickerPositionX;

    @Column(name="STICKER_POSITION_Y")
    private String stickerPositionY;

    @Column(name="THUMB_NAIL")
    private String thumbNail;

    public TaleInfo(String id, String fontStyle, String fontSize, String fontColor, String fontPositionX, String fontPositionY, String coverColor, String sticker, String stickerPositionX, String stickerPositionY, String thumbNail) {
        this.id = id;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontPositionX = fontPositionX;
        this.fontPositionY = fontPositionY;
        this.coverColor = coverColor;
        this.sticker = sticker;
        this.stickerPositionX = stickerPositionX;
        this.stickerPositionY = stickerPositionY;
        this.thumbNail = thumbNail;
    }
}
