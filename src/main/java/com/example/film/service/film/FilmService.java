package com.example.film.service.film;

import com.example.film.domain.*;
import com.example.film.repository.FilmActorRepository;
import com.example.film.repository.FilmCategoryRepository;
import com.example.film.repository.FilmRepository;
import com.example.film.service.film.request.FilmSaveRequest;
import com.example.film.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmActorRepository filmActorRepository;

    public void create(FilmSaveRequest request){
        var film = AppUtil.mapper.map(request, Film.class);
        film = filmRepository.save(film);
        var filmCategories = new ArrayList<FilmCategory>();

        for (String idCategory: request.getCategories()) {
            Category category = new Category(Long.valueOf(idCategory));
            filmCategories.add(new FilmCategory(film,category));
        }
        filmCategoryRepository.saveAll(filmCategories);

        var filmActors = new ArrayList<FilmActor>();

        for (String idActor: request.getActors()) {
            Person actor = new Person(Long.valueOf(idActor));
            filmActors.add(new FilmActor(actor,film));
        }
        filmActorRepository.saveAll(filmActors);
    }
}
