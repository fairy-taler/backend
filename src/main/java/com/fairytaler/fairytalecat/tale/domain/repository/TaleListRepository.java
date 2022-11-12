package com.fairytaler.fairytalecat.tale.domain.repository;

import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaleListRepository extends MongoRepository<TaleList, String>{
    List<TaleList> findByMemberCode(String memberCode);
}

