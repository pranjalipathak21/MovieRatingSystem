package com.movie.catalog.webapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.catalog.webapp.model.CatalogItem;
import com.movie.catalog.webapp.model.Rating;
import com.movie.catalog.webapp.model.UserRating;
import com.movie.catalog.webapp.service.MovieInfoService;
import com.movie.catalog.webapp.service.UserRatingService;

@RestController
@CrossOrigin(origins = "*") // Allow requests from any origin
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private UserRatingService userRatingService;

	@Autowired
	private MovieInfoService movieInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogController.class);

	@RequestMapping("/{userId}")
	public ResponseEntity<List<CatalogItem>> getCatalogs(@PathVariable("userId") long userId) {
		try {

			UserRating userRating = userRatingService.getUserRating(userId);
			List<CatalogItem> catalogs = null;
			
			if(userRating!= null)
			{
				LOGGER.info("UserRating userId " + userRating.getUserId());

				for (Rating rating : userRating.getRatings()) {
					catalogs = movieInfoService.getCatalogItems(rating);
				}
				LOGGER.info("getCatalog success");
			}

			return new ResponseEntity<>(catalogs, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("getCatalog eception " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}