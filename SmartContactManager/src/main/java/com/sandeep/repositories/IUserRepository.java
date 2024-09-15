package com.sandeep.repositories;

import java.util.Optional;

import com.sandeep.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sandeep.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, String> {
	

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
