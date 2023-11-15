package com.movie.data.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.data.webapp.repository.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
