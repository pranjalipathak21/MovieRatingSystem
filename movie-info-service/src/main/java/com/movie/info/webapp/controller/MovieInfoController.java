package com.movie.info.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.info.webapp.model.Movie;
import com.movie.info.webapp.service.MovieInfoService;

@RestController
@CrossOrigin(origins = "*") // Allow requests from any origin
public class MovieInfoController {

	@Autowired
	private MovieInfoService movieService;

	@RequestMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = movieService.getAllMovies();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@RequestMapping("/movies/{movieId}")
	public ResponseEntity<?> getMovie(@PathVariable long movieId) {
		Movie movie = movieService.getMovie(movieId);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@RequestMapping(method = RequestMethod.POST, value = "/movies")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		// Implement code to create a new movie entry
		Movie createdMovie = movieService.addMovie(movie);
		if (createdMovie != null) {
            return new ResponseEntity<>(createdMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/movies/{movieId}")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable Long movieId) {
			Movie updatedMovie = movieService.updateMovie(movie, movieId);
	        if (updatedMovie != null) {
	            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		}

	@RequestMapping(method = RequestMethod.DELETE, value = "/movies/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable long movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
