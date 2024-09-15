package com.sandeep.services;

import java.util.List;
import java.util.Optional;

import com.sandeep.entities.UserEntity;
import com.sandeep.entities.UserEntity;

public interface IUserService {

	UserEntity saveUser(UserEntity user);

	Optional<UserEntity> geUserById(String id);

	Optional<UserEntity> updateUser(UserEntity user);

	void deleteUser(String id);

	boolean isUserExist(String userId);

	boolean isUserExistByEmail(String email);

	List<UserEntity> getAllUser();

	UserEntity getUserByEmail(String email);
}
