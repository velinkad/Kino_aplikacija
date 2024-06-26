package com.example.kino_aplikacija.service;

import com.example.kino_aplikacija.model.Movie;
import com.example.kino_aplikacija.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository  movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> getMoviebyId(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
