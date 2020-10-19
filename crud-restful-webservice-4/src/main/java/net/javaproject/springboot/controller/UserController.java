package net.javaproject.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.javaproject.springboot.entity.User;
import net.javaproject.springboot.exception.ResouceNotFoundException;
import net.javaproject.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public java.util.List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value="id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User not found with ID:"+userId));
	}
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser =  this.userRepository.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User not found with ID:"+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	
	}
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
		User existingUser =  this.userRepository.findById(userId)
				.orElseThrow(()->new ResouceNotFoundException("User not found with ID:"+userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
		
}
