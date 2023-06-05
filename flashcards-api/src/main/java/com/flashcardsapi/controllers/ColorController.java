package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcardsapi.entities.db.Color;
import com.flashcardsapi.services.ColorService;

import java.util.List;

@RestController
@RequestMapping("colors")
@AllArgsConstructor
public class ColorController {
    private ColorService colorService;

    @GetMapping()
    public List<Color> getAllColors() {
        return colorService.getAllColors();
    }
}
