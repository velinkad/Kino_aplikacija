package com.example.kino_aplikacija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kino_aplikacija.model.Movie;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Long>{

}

