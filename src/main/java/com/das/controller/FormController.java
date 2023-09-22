package com.das.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.das.dto.UserDTO;
import com.das.entity.User;

@Controller
public class FormController {
		
	
	@PostMapping("/form")
	public ModelAndView loadForm() {
	ModelAndView mav = new ModelAndView();
	mav.addObject("user", new User());
	mav.setViewName("RegistrationPage");
	return mav;
	}
	@PostMapping("/submit")
	public ModelAndView handleSubmitButton(UserDTO dto) {
	ModelAndView mav = new ModelAndView();
	mav.setViewName("SuccessView");
	return mav;
	} 
}
