package com.fairytaler.fairytalecat.mongoTest.repository;

import com.fairytaler.fairytalecat.mongoTest.model.MongoDBTestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDBTestRepository extends MongoRepository<MongoDBTestModel, String> {
    MongoDBTestModel findByName(String name);
}