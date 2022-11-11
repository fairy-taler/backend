package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "TB_TAIL_INFO")
public class TaleInfo {

    @javax.persistence.Id
    @Id
    @Column(name="ID")
    private String id;              // 고유 번호

    @Column(name="FONT_STYLE")
    private String fontStyle;

    @Column(name="FONT_SIZE")
    private String fontSize;

    @Column(name="FONT_COLOR")
    private String fontColor;

    @Column(name="COVER_COLOR")
    private String coverColor;

    @Column(name="STICKER")
    private String sticker;

    @Column(name="STICKER_POSITION")
    private String stickerPosition;

    @Column(name="THUMB_NAIL")
    private String thumbNail;

}
