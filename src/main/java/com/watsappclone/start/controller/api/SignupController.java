package com.watsappclone.start.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;

@Controller
public class SignupController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/signup")
	public String showSignupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSignup(String name, String email, String password, String phoneNumber) {
		User user = new User(name, email, password, phoneNumber, null, null, null );
		userRepository.save(user);
		return "redirect:/signup";
	}
}