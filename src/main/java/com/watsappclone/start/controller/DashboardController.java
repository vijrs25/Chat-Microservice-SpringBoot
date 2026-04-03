package com.watsappclone.start.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.watsappclone.start.entity.User;

import jakarta.servlet.http.HttpSession;

public class DashboardController {

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model mdl) {
		
		User user = (User)session.getAttribute("username");
		System.out.println("dahsboard controller");
		
		if(user == null) {
			return "login";
		}
		
		mdl.addAttribute("user",user);
		return "dashboard";

	}
}