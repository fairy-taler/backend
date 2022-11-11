package com.fairytaler.fairytalecat.tale.domain.repository;

import com.fairytaler.fairytalecat.tale.domain.model.TaleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaleInfoRepository extends JpaRepository<TaleInfo, String> {

    TaleInfo findTaleInfoById(String id);
}