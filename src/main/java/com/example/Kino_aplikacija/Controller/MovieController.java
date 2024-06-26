package com.example.Kino_aplikacija.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kino_aplikacija.model.Movie;
import com.example.kino_aplikacija.model.Screening;
import com.example.kino_aplikacija.service.MovieService;
import com.example.kino_aplikacija.service.ScreeningService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreeningService screeningService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public  ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Optional<Movie> movie = movieService.getMoviebyId(id);
        return  movie.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public  ResponseEntity<List<Movie>> getAlMovies () {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    @PostMapping("/{movie_id}/screening")
    public ResponseEntity<?> addScreening(@PathVariable Long movie_id, @RequestBody List<Screening> screenings) {
        Optional<Movie> movie = movieService.getMoviebyId(movie_id);

        if (movie.isPresent ()) {
            Movie currentMovie = movie.get();

            for (Screening screening : screenings) {
                screening.setMovie(currentMovie);
                screening.setEndTime(screening.getStartTime().plusMinutes(currentMovie.getRunningTime() + 30));
                // Provjera preklapanja sa postojećim prikazivanjima

                for (Screening existingScreening : currentMovie.getScreenings()) {
                    if (existingScreening.getHall().equals(screening.getHall())&&
                            (screening.getStartTime().isBefore(existingScreening.getEndTime())&&
                                    screening.getEndTime().isAfter(existingScreening.getStartTime()))) {
                        return  new  ResponseEntity<>(HttpStatus.BAD_REQUEST);

                    }
                }
                currentMovie.getScreenings().add(screening);
            }
            movieService.saveMovie(currentMovie);
            return new ResponseEntity<>(currentMovie.getScreenings(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
