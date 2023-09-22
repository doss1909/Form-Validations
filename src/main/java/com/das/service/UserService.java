package com.das.service;

import com.das.entity.User;
import com.das.login.Login;

public interface UserService {
	
	User saveUser(User user);
	
	String login(Login login);
	
}
