package com.task.movieapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.movieapi.model.MovieModel;
import com.task.movieapi.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	 private final MovieService movieService;

	    public MovieController(MovieService movieService) {
	        this.movieService = movieService;
	    }
	    @GetMapping("/start")
	    public String start() {
	        return "Movie API is running";
	    }

	    // Add new movie
	    @PostMapping
	    public ResponseEntity<?> addMovie(@RequestBody MovieModel movie) {

	        // Input Validation
	        if (movie.getName() == null || movie.getName().isEmpty()) {
	            return ResponseEntity.badRequest().body("Movie name is required");
	        }

	        if (movie.getRating() < 0 || movie.getRating() > 10) {
	            return ResponseEntity.badRequest().body("Rating must be between 0 and 10");
	        }

	        MovieModel savedMovie = movieService.addMovie(movie);
	        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
	    }

	    // Get movie by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<?> getMovie(@PathVariable Long id) {
	        MovieModel movie = movieService.getMovieById(id);

	        if (movie == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Movie not found with id " + id);
	        }

	        return ResponseEntity.ok(movie);
	    }
}
