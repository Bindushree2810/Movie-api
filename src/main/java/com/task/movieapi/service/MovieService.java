package com.task.movieapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.task.movieapi.model.MovieModel;

@Service
public class MovieService {
	
	private final List<MovieModel> movies = new ArrayList<>();
    private long idCounter = 1;

    // Add movie
    public MovieModel addMovie(MovieModel movie) {
        movie.setId(idCounter++);
        movies.add(movie);
        return movie;
    }

    // Get movie by ID
    public MovieModel getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
