package com.epam.controller;


import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.beans.User;
import com.epam.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/{user_id}", produces = "application/json")
	public ResponseEntity<User> getUser(@PathVariable("user_id") long id) {
		LOG.info("****** Entered UserController to get the data for a particular ID from Database *******");
        Optional<User> response=userService.getUser(id);
		LOG.debug("User response :: {}",response);
		return new ResponseEntity<>(response.get(), OK);
	}

	@GetMapping(value = "/",consumes= "application/json", produces = "application/json")
	public ResponseEntity<List<User>> getAllUsers() {
		LOG.info("****** Entered Usercontroller to get complete from Database *******");

		List<User> response=userService.getAllUsers();
		LOG.debug("User response :: {}",response);
		return new ResponseEntity<>(response, OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> addUser(@RequestBody User user) {

		LOG.info("****** Entered Usercontroller to Save the data into Database *******");

		String response=userService.addUserDetails(user);
		LOG.debug("User response :: {}",response);
		return new ResponseEntity<>(response, OK);

	} 
	
	@PutMapping(path="/{user_id}", produces = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable("user_id") long id, @RequestBody User user) {
		LOG.info("****** Entered Usercontroller to update the data from Database *******");

		String response=userService.updateUser(id,user);
		LOG.debug("User response :: {}",response);
		return new ResponseEntity<>(response, OK);
   }
	
	@DeleteMapping(path = "/{user_id}", produces = "application/json")
	public ResponseEntity<String> deleteUser(@PathVariable("user_id") long id) {

		LOG.info("****** Entered Usercontroller to delete the data from Database *******");

		String response=userService.deleteUser(id);
		LOG.debug("User response :: {}",response);
		return new ResponseEntity<>(response, OK);
     } 
	
}
