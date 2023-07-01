package com.in.sakilaproject.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.in.sakilaproject.entities.Actor;
//import com.in.sakilaproject.entities.Film;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Integer>{
    List<Actor> findActorsByFirstNameAndLastName(String firstName, String lastName);
    List<Actor> findActorsByFirstName(String firstName);
    List<Actor> findActorsByLastName(String lastName);
    Actor getActorByActorId(Integer id);
}
