package com.movie.info.webapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.info.webapp.model.Movie;
import com.movie.info.webapp.repository.MovieRepository;

@Service
public class MovieInfoService {

	@Autowired
	private MovieRepository movieRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieInfoService.class);

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			LOGGER.info("parseDate success");
		} catch (ParseException e) {
			LOGGER.info("parseDate exception " + e.getMessage());
		}
		return date;
	}

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		movieRepository.findAll().forEach(movies::add);
		return movies;
	}

	public Movie getMovie(long movieId) {
		return movieRepository.findById(movieId).orElse(null);
	}

	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	public Movie updateMovie(Movie movie, Long movieId) {
		if (movieRepository.existsById(movieId)) {
            movie.setMovieId(movieId);
            return movieRepository.save(movie);
        } else {
            return null; // Handle not found case
        }
	}

	public void deleteMovie(long movieId) {
		movieRepository.deleteById(movieId);
	}

}
