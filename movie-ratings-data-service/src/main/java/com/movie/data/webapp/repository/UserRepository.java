package com.movie.data.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.data.webapp.repository.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
