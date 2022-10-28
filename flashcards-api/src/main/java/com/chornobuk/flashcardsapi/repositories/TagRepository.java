package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.Tag;
import com.chornobuk.flashcardsapi.entities.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findAllByUser(User user);
}
