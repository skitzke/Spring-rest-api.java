package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
