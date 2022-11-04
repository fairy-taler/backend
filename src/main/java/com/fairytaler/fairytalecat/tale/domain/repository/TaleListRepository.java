package com.fairytaler.fairytalecat.tale.domain.repository;

import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaleListRepository extends MongoRepository<TaleList, String>{

    TaleList findByMemberCode(String memberCode);
}

