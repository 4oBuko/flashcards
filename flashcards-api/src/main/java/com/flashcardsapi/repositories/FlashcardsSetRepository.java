package com.flashcardsapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.FlashcardsSet;
import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;

import java.util.List;

public interface FlashcardsSetRepository extends CrudRepository<FlashcardsSet, Long> {
    List<FlashcardsSet> getFlashcardsSetByUser(User user);

    void deleteById(Long id);

    List<FlashcardsSet> findAllByTags(Tag tag);
}
