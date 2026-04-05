package com.watsappclone.start.controller.api;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/me")
	public User getUser(HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");

		if (userid != 0) {
			System.out.println("userid " + userid);
			return userRepository.findById(userid).orElse(null);
		}
		return null;
	}
	
	@GetMapping("/users")
	public List<User> getallUsers(HttpSession session) {
		List<User> userList = new ArrayList<>();
		Long userid = (Long) session.getAttribute("userid");
		userList = userRepository.findAll();
		
		if(userList == null) {
			return null;
		}
		return userList.stream().filter( user -> !user.getId().equals(userid)).toList() ;
	}
}
