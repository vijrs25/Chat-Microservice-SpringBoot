package com.watsappclone.start.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public User getUser(HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");

		if (userid != 0) {
			System.out.println("userid " + userid);
			return userRepository.findById(userid).orElse(null);
		}
		return null;
	}
}
