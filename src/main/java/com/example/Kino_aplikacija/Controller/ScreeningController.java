package com.example.kino_aplikacija.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kino_aplikacija.model.Screening;
import com.example.kino_aplikacija.service.ScreeningService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/screening")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @GetMapping
    public ResponseEntity<List<Screening>> getAllScreening() {
        List<Screening> screenings =screeningService.getAllScreenings();
        return  ResponseEntity.ok(screenings);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteScreening(@PathVariable Long id) {
        Optional<Screening> screening =screeningService.getScreeningbyId(id);
        if (screening.isPresent()) {
            screeningService.deleteScreeningById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else  {
            return new
                    ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
