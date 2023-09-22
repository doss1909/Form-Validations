package com.das.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.das.entity.User;
import com.das.exceptions.UserNotFoundException;
import com.das.login.Login;
import com.das.repo.UserRepo;
import com.das.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public User saveUser(User user) {
		Optional<User> findByEmail = userRepo.findByEmail(user.getEmail());
		if (!findByEmail.isPresent()) {
			if (user.getName()!=null) {
				return userRepo.save(user);
			}
		}
		return null;
	}

	@Override
	public String login(Login login) {
	Optional<User> findByEmail = userRepo.findByEmail(login.getEmail());
	if (!findByEmail.isPresent()) {
		throw new UserNotFoundException(login.getEmail()+" "+login.getPassword()+" are invalid");
		//return "Login Failed";
		}
	User user = findByEmail.get();
	user.getPassword().equals(login.getPassword());
	return "Login Successfull";
	}

}
