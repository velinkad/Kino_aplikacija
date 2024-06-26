package com.example.kino_aplikacija.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Getter i Setter metode

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
