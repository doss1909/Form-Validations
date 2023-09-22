package com.das.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.das.dto.UserDTO;
import com.das.entity.User;
import com.das.login.Login;
import com.das.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	private ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		User saveUser = userService.saveUser(user);
		if (saveUser!=null) {
			return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Email already registered",HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	private ResponseEntity<String> loginUser(@Valid @RequestBody Login login){
		
		String message = userService.login(login);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
}
