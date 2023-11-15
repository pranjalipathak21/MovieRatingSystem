package com.movie.data.webapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.data.webapp.repository.RatingRepository;
import com.movie.data.webapp.repository.UserRepository;
import com.movie.data.webapp.repository.model.Rating;
import com.movie.data.webapp.repository.model.User;
import com.movie.data.webapp.view.model.ViewRating;
import com.movie.data.webapp.view.model.ViewUser;

@Service
public class RatingDataService {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingDataService.class);

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

	public ViewRating getRatingsForAllMoviesRatedByUser(long userId) {
		Iterable<Rating> usersRatings = ratingRepository.findAll();
		ViewRating viewRating = new ViewRating();
		viewRating.setUserId(userId);
		for (Rating rating : usersRatings) {
			if (rating.getUser().getUserId() == userId) {

				viewRating.getRatings().add(rating);
			}
		}

		return viewRating;
	}

	public ViewUser getAllUsersRatedGivenMovie(long movieId) {
		Iterable<Rating> usersRatings = ratingRepository.findAll();
		ViewUser viewUser = new ViewUser();
		viewUser.setMovieId(movieId);
		for (Rating rating : usersRatings) {
			if (rating.getMovieId() == movieId) {
				viewUser.getUsers().add(userRepository.findById(rating.getUser().getUserId()).get());
			}
		}
		
		return viewUser;
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

}
