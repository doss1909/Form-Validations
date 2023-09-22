package com.das.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.das.entity.User;
import com.das.exceptions.UserNotFoundException;
import com.das.login.Login;
import com.das.repo.UserRepo;
import com.das.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepo userRepo;
	
	@Test
	public void testRegisterUserIfEmailPresent() {
		User user = new User(1, "mohan", "mohan@gmail.com", "mohan1234", 24, "male");
		when(userRepo.save(user)).thenReturn(user);
		
		User saveUser = userService.saveUser(user);
		assertEquals(user, saveUser);
	}
	
	@Test
	public void testRegisterUserIfNameIsNull() {
		User user = new User(1, null, "mohan@gmail.com", "mohan1234", 24, "male");
		when(userRepo.save(user)).thenReturn(null);
		
		User saveUser = userService.saveUser(user);
		assertEquals(null, saveUser);
	}
	
	@Test
	public void testRegisterUserIfEmailIsNull() {
		User user = new User(1, "mohan", null, "mohan1234", 24, "male");
		when(userRepo.save(user)).thenReturn(null);
		
		User saveUser = userService.saveUser(user);
		assertEquals(null, saveUser);
	}
	
	@Test
	public void testLoginValidEmail() {
		Login login = new Login();
		login.setEmail("mohan@gmail.com");
		login.setPassword("mohan1234");
		User user = new User(1, "mohan", "mohan@gmail.com", "mohan1234", 24, "male");
		
		Optional<User> optUser = Optional.of(user);
		when(userRepo.findByEmail("mohan@gmail.com")).thenReturn(optUser);
		
		String loginMsg = userService.login(login);
		
		assertEquals("Login Successfull", loginMsg);
	}
	
	@Test
	public void testLoginIfNotValidEmail() {
		Login login = new Login();
		login.setEmail("mohan123@gmail.com");
		login.setPassword("mohan1234");
		User user = new User(1, "mohan", "mohan@gmail.com", "mohan1234", 24, "male");
		
		Optional<User> optUser = Optional.of(user);
		when(userRepo.findByEmail("mohan@gmail.com")).thenReturn(optUser);
		
		assertThrows(UserNotFoundException.class, () -> userService.login(login));
	}
	
}
