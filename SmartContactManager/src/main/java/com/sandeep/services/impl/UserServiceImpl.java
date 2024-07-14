package com.sandeep.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sandeep.helper.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sandeep.entities.User;
import com.sandeep.helper.ResourceNotFoundException;
import com.sandeep.repositories.IUserRepository;
import com.sandeep.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	private IUserRepository userRepository ;

	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	private  Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public User saveUser(User user) {
		//user id : have to generate 
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		// password encode
		// user.setpassword(userId)
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// set user role
		user.setRoleList(List.of(ConstantUtils.ROLE_USER));

		logger.info((user.getProvider().toString()));
		return userRepository.save(user);
	}

	@Override
	public Optional<User> geUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> updateUser(User user) {
		
	User user2 = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
	
	user2.setName(user.getName());
	user2.setEmail(user.getEmail());
	user2.setPassword(user.getPassword());
	user2.setAbout(user.getAbout());
	user2.setPhoneNumber(user.getPhoneNumber());
	user2.setProfilePicLink(user.getProfilePicLink());
	user2.setEnabled(user.isEnabled());
	user2.setEmailVerified(user.isEmailVerified());
	user2.setPhoneVerified(user.isPhoneVerified());
	user2.setProvider(user.getProvider());
	user2.setProviderUserId(user.getProviderUserId());
	
	User sUser = userRepository.save(user2);
	
	return Optional.ofNullable(sUser);
	}

	@Override
	public void deleteUser(String id) {
		User user2 = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		
		userRepository.delete(user2);
	}
	

	@Override
	public boolean isUserExist(String userId) {
	 User user2 = userRepository.findById(userId).orElse(null);
		return  user2!= null ? true :false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		return user!=null ? true :false;
	}

	@Override
	public List<User> getAllUser() {
	return userRepository.findAll();
	}



	

}
