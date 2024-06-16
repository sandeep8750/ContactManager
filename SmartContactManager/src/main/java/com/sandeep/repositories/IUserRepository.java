package com.sandeep.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandeep.entities.User;

public interface IUserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);
	
}
