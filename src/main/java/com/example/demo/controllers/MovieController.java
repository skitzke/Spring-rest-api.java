package com.example.demo.controllers;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        movieService.saveMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> listMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
}
