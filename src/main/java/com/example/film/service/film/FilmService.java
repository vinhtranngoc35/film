package com.example.film.service.film;

import com.example.film.domain.*;
import com.example.film.repository.FilmActorRepository;
import com.example.film.repository.FilmCategoryRepository;
import com.example.film.repository.FilmRepository;
import com.example.film.service.film.request.FilmSaveRequest;
import com.example.film.service.film.response.FilmListResponse;
import com.example.film.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmActorRepository filmActorRepository;

    public Long create(FilmSaveRequest request){
        //cơm
//        Film film = new Film();
//        film.setName(request.getName());
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        film.setPublishDate(LocalDate.parse(request.getPublishDate(), format));
//        Person director = new Person(Long.valueOf(request.getDirector().getId()));
//        film.setDirector(director);
        //lib
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
        return film.getId();
    }

    public List<FilmListResponse> getAll(){
        return filmRepository.findAll().stream().map(film -> FilmListResponse.builder()
                .id(film.getId())
                .name(film.getName())
                .director(film.getDirector().getName())
                .publishDate(film.getPublishDate())
                .actors(film.getFilmActors()
                        .stream().map(filmActor -> filmActor.getActor().getName())
                        .collect(Collectors.joining(", ")))
                .categories(film.getFilmCategories()
                        .stream().map(filmCategory -> filmCategory.getCategory().getName())
                        .collect(Collectors.joining(", "))).build()).collect(Collectors.toList());
    }
}
