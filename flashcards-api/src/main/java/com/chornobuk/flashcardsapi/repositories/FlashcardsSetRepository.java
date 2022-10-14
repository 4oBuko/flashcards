package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.FlashcardsSet;
import org.springframework.data.repository.CrudRepository;

public interface FlashcardsSetRepository extends CrudRepository<FlashcardsSet, Long> {
}
