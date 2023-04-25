package com.flashcardsapi.services;

import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.db.Color;
import com.flashcardsapi.repositories.ColorRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ColorService {
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return StreamSupport.stream(colorRepository.findAll().spliterator(), false)
                .toList();
    }

    public Color getColorById(long id) {
        return colorRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
    }
}
