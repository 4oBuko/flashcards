package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.FlashcardsSet;
import com.chornobuk.flashcardsapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlashcardsSetRepository extends CrudRepository<FlashcardsSet, Long> {
    List<FlashcardsSet> getFlashcardsSetByUser(User user);

    void deleteById(Long id);
}
