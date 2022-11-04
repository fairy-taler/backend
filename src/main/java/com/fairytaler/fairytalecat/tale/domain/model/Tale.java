package com.fairytaler.fairytalecat.tale.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "tale")
public class Tale {

    @Id
    private String taleCode;

    private String title;
    private String createAt;
    private List<TalePage> pages;
}


