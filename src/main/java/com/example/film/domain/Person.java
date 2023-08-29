package com.example.film.domain;

import com.example.film.domain.enums.EGender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @OneToMany(mappedBy = "director")
    private Set<Film> films;

    @OneToMany(mappedBy = "actor")
    private Set<FilmActor> filmActors;

    @OneToMany(mappedBy = "person")
    private Set<PersonRole> personRoles;

    public Person(Long id) {
        this.id = id;
    }
}
