package com.sandeep.services;

import java.util.List;
import java.util.Optional;

import com.sandeep.entities.User;

public interface IUserService {

	User saveUser(User user);

	Optional<User> geUserById(String id);

	Optional<User> updateUser(User user);

	void deleteUser(String id);

	boolean isUserExist(String userId);

	boolean isUserExistByEmail(String email);

	List<User> getAllUser();
}
