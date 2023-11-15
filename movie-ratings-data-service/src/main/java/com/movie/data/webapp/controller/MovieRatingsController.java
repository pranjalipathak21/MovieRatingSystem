package com.movie.data.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.data.webapp.repository.model.User;
import com.movie.data.webapp.service.RatingDataService;
import com.movie.data.webapp.view.model.ViewRating;
import com.movie.data.webapp.view.model.ViewUser;

@RestController
@CrossOrigin(origins = "*") // Allow requests from any origin
@RequestMapping("/ratingsdata")
public class MovieRatingsController {

	@Autowired
	private RatingDataService ratingDataService;

	@RequestMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = ratingDataService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping("/users/{userId}")
	public ResponseEntity<ViewRating> getRatingsForAllMoviesRatedByUser(@PathVariable("userId") long userId) {
		ViewRating viewRating = ratingDataService.getRatingsForAllMoviesRatedByUser(userId);
		
		if (viewRating != null) {
            return new ResponseEntity<>(viewRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

	@RequestMapping("/movies/{movieId}")
	public ResponseEntity<ViewUser> getAllUsersRatedGivenMovie(@PathVariable("movieId") long movieId) {
		ViewUser viewUser = ratingDataService.getAllUsersRatedGivenMovie(movieId);
		
		if (viewUser != null) {
            return new ResponseEntity<>(viewUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}

}
