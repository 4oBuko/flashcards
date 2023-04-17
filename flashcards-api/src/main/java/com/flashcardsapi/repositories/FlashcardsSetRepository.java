package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.FlashcardsSet;

import java.util.List;

public interface FlashcardsSetRepository extends CrudRepository<FlashcardsSet, Long> {

    void deleteById(Long id);

    List<FlashcardsSet> findAllByUser_id(Long userid);
}
