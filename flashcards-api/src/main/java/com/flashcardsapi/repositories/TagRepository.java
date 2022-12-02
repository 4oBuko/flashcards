package com.flashcardsapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findAllByUser(User user);

    void deleteById(Long id);
}
