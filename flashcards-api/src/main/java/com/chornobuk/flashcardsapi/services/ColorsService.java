package com.chornobuk.flashcardsapi.services;

import com.chornobuk.flashcardsapi.entities.Color;
import com.chornobuk.flashcardsapi.repositories.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ColorsService {
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return StreamSupport.stream(colorRepository.findAll().spliterator(), false)
                .toList();
    }

    public Color getColorById(long id) {
        return colorRepository.findById(id).orElse(null);
    }
}
