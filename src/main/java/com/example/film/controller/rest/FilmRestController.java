package com.example.film.controller.rest;

import com.example.film.service.film.FilmService;
import com.example.film.service.film.request.FilmSaveRequest;
import com.example.film.service.film.response.FilmListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/films")
@AllArgsConstructor
public class FilmRestController {

    private final FilmService filmService;


    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid FilmSaveRequest request) {

        return new ResponseEntity<>(filmService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FilmListResponse>> list(@PageableDefault(size = 2) Pageable pageable,
                                                       @RequestParam(defaultValue = "") String search) {
        return new ResponseEntity<>(filmService.getAll(pageable, search), HttpStatus.OK);
    }

}
