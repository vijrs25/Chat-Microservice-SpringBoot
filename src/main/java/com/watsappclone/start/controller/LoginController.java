package com.watsappclone.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.watsappclone.start.entity.User;
import com.watsappclone.start.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	    @PostMapping("/login")
	    public String handleLogin(String email, String password, HttpSession session) {
	        User user = userRepository.findByEmail(email);
	        if (user == null || !user.getPassword().equals(password)) {
	            return "login";
	        }
	        session.setAttribute("loggedInUser", user);
	        return "redirect:/dashboard";
	    }

	    @GetMapping("/dashboard")
	    public String showDashboard(HttpSession session, Model model) {
	        User user = (User) session.getAttribute("loggedInUser");
	        if (user == null) {
	            return "redirect:/login";
	        }
	        System.out.println("Dashboard controller called");
	        model.addAttribute("user", user);
	        return "dashboard"; // this resolves templates/dashboard.html
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        System.out.println("Logout controller called");
	        session.invalidate();
	        return "redirect:/login";
	    }

}
