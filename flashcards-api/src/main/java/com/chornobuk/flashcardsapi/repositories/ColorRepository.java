package com.chornobuk.flashcardsapi.repositories;

import com.chornobuk.flashcardsapi.entities.Color;
import org.springframework.data.repository.CrudRepository;


public interface ColorRepository extends CrudRepository<Color,Long> {
}
