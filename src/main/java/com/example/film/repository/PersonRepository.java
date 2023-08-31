package com.example.film.repository;

import com.example.film.domain.FilmActor;
import com.example.film.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query(value = "SELECT p FROM Person p " +
            "JOIN PersonRole pr ON p.id = pr.person.id WHERE  pr.role.name = :name")
    List<Person> getPersonsByRole(String name);

}
