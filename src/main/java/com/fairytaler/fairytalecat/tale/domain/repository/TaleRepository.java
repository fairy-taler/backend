package com.fairytaler.fairytalecat.tale.domain.repository;

import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaleRepository extends MongoRepository<Tale, String>{}

