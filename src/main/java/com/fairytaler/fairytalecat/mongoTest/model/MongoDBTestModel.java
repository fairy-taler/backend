package com.fairytaler.fairytalecat.mongoTest.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "user")
public class MongoDBTestModel {
    @Id
    private int id;

    private String name;
    private int age;
}
