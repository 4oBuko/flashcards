package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.Color;
import com.chornobuk.flashcardsapi.services.ColorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("colors")
@AllArgsConstructor
public class ColorsController {
    private ColorsService colorsService;
    @GetMapping()
    public ResponseEntity<List<Color>> getAllColors() {
        return ResponseEntity.ok(colorsService.getAllColors());
    }
    // todo: I can join this controller to Tags controller because colors use only for tags.
}
