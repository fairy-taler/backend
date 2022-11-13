package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
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

}
