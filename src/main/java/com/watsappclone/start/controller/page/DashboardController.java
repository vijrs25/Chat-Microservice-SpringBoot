package com.watsappclone.start.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.watsappclone.start.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		Long userid =  (Long) session.getAttribute("userid");
		
		System.out.println("dashboard");
		if (userid == 0 ) {
			return "redirect:/login.html";
		}
		System.out.println("Dashboard controller called");
		return "redirect:/dashboard.html"; // this resolves templates/dashboard.html
	}
}	