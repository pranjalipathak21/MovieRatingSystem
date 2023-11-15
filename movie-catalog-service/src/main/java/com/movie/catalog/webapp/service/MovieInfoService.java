package com.movie.catalog.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.webapp.model.CatalogItem;
import com.movie.catalog.webapp.model.Movie;
import com.movie.catalog.webapp.model.Rating;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieInfoService.class);

	public List<CatalogItem> getCatalogItems(Rating rating) {
//		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		Movie movie = restTemplate.getForObject("http://localhost:10001/movies/" + rating.getMovieId(), Movie.class);
		List<CatalogItem> catalogs = new ArrayList<>();
		LOGGER.info("Movie movieId " + movie.getMovieId());
		catalogs.add(new CatalogItem(movie.getMovieName(), movie.getMovieGenre(), movie.getMovieReleaseDate(),
				movie.getMovieDescription(), rating.getMovieRating(), rating.getUserReview()));
		return catalogs;
	}
}
