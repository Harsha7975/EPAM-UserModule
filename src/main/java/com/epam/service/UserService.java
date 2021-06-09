package com.epam.service;

import org.springframework.stereotype.Service;

import com.epam.beans.User;
import com.epam.exception.UserNotFoundException;
import com.epam.repository.UserRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	public String addUserDetails(User user) {
		String message="";
		try {
			LOG.info("Entered into service adddetails method");
			if(userRepository.findByUserName(user.getUserName())!=null) {
				message="Name is already present,please use different name";
			}else {
				userRepository.save(user);
				message="Details are saved successfully";
			}

		}catch (Exception e) {
			message="Details are not saved successfully";
		}
		return message;
	}
	
	public Optional<User> getUser(long id) {
		Optional<User> userDetails=userRepository.findById(id);
		if(!(userDetails.isPresent())){
			throw new UserNotFoundException("There is no data for this Id!!!");
		} else {
			return userDetails;
		}
	}
	
	public List<User> getAllUsers() {
		List<User> userDetails=userRepository.findAll();
		if(userDetails.isEmpty()){
			throw new UserNotFoundException("There is no data in Database!!!");
		} else {
			return userDetails;
		}

	}
	
	public String updateUser(long id,User user) {
		String message="";
        if(userRepository.findById(id).isPresent()){
			User usr=new User();
			usr.setUserId(id);
			usr.setUserName(user.getUserName());
			usr.setPassword(user.getPassword());
			usr.setRole(user.getRole());
			usr.setDob(user.getDob());
			userRepository.save(usr);
			return message="Update  is  happened Successfully!!!";
		}else {
			throw new UserNotFoundException("There is no id to update!!!");
		}
	}


	public String deleteUser(long id) {
		String message="";
		if(userRepository.findById(id).isPresent()){
			userRepository.deleteById(id);
			return 	message="Delete is  happened Successfully!!!";
		}else {
			throw new UserNotFoundException("There is no id to delete!!!");
		}
	}

}
