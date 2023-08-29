package com.example.film.controller.rest;

import com.example.film.service.film.FilmService;
import com.example.film.service.film.request.FilmSaveRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/films")
@AllArgsConstructor
public class FilmRestController {

    private final FilmService filmService;


    @PostMapping
    public void create(@RequestBody @Valid FilmSaveRequest request){
        filmService.create(request);
    }
}
