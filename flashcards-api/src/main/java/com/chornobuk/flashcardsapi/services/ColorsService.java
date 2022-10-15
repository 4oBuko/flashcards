package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.Color;
import com.chornobuk.flashcardsapi.repositories.ColorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ColorsService {
    private ColorsRepository colorsRepository;

    public List<Color> getAllColors() {
        return StreamSupport.stream(colorsRepository.findAll().spliterator(), false)
                .toList();
    }
}
