package com.watsappclone.start.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public String handleLogin(@RequestParam String email, @RequestParam String password, HttpSession session) {
		User user = userRepository.findByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			return "redirect:/login.html";
		}
		session.setAttribute("userid", user.getId());
		return "redirect:/dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("Logout controller called");
		session.invalidate();
		return "redirect:/login";
	}

}
