package com.movie.catalog.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.catalog.webapp.model.UserRating;

@Service
public class UserRatingService {

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRatingService.class);

	public UserRating getUserRating(long userId) {
		UserRating userRating = restTemplate.getForObject("http://localhost:11001/ratingsdata/users/" + userId,
				UserRating.class);
		LOGGER.info("User userId " + userId);
		// UserRating userRating = restTemplate
		//		.getForObject("http://localhost:11001/ratingsdata/users/" + userId, UserRating.class);
		return userRating;
	}
}
