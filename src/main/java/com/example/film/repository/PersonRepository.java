package com.example.film.repository;

import com.example.film.domain.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<FilmActor, Long> {
}
