package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcardsapi.entities.Color;
import com.flashcardsapi.services.ColorsService;

import java.util.List;

@RestController
@RequestMapping("colors")
@AllArgsConstructor
public class ColorsController {
    private ColorsService colorsService;

    @GetMapping()
    public List<Color> getAllColors() {
        return colorsService.getAllColors();
    }
}
