package com.flashcardsapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.entities.db.User;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findAllByUser(User user);

    List<Tag> findAllByUser_id(Long userId);
}
