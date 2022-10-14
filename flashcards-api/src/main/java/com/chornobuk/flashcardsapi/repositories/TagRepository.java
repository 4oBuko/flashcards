package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
