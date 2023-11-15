package com.movie.info.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.movie.info.webapp.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}