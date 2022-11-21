package com.fairytaler.fairytalecat.tale.domain.repository;

import com.fairytaler.fairytalecat.tale.domain.model.Tale;
import com.fairytaler.fairytalecat.tale.domain.model.TaleList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaleListRepository extends MongoRepository<TaleList, String>{
    List<TaleList> findByMemberCode(String memberCode);

    Page<TaleList> findAll(Pageable pageable);
    Page<TaleList> findByTitleContaining(String title, Pageable pageable);
}

