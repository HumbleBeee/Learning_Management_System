package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Errors;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/nav")
public class NavigationController {

	@Autowired
	UserService uService;
	
	@GetMapping("/home")
	public String goHome() {
		return "index";
	}
	
	
	@GetMapping("/login")
	public String getLoginPage(Model theModel)
	{
		Errors e=new Errors();
		theModel.addAttribute("loginError", e);
		return "login";
	}
	
//	@PostMapping("/login")
//	public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model theModel) {
//		boolean userExists = uService.checkEmail(email);
//		if(userExists) {
//			return "userDashboard";
//		}
//		else {
//			theModel.addAttribute("error", "Email is not registered. Please register and then login.");
//	        return "login";
//	        //return "index";
//		}
//	}
	
	@GetMapping("/register")
	public String register() {
		return "Register";
	}
	
	@PostMapping("/adduser")
	public String adduser(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") 
	String password,@RequestParam("role") String role, Model theModel) {
		boolean emailExists = uService.checkEmail(email);
		if(emailExists == false) {
			Users user = new Users();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(role);
			uService.addUser(user);
			System.out.println("user registration successful");
			return "redirect:/nav/login";
		}
		else {
			theModel.addAttribute("error", "User already exists with this email. Please try a different email or login.");
	        return "register";
			
		}
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, 
			HttpSession session, Model theModel) {
		if(uService.checkEmail(email)) {
			boolean val = uService.validate(email, password);
			if(val == true) {
				Users user = uService.getByEmail(email);
				session.setAttribute("loggedInUser", user);
				if(uService.getUserRole(email).equals("trainer")) {
					return "TrainerHome";
				}
				else {
					return "StudentHome";
				}
			}
			else {
				theModel.addAttribute("error", "Email is not registered. Please register and then login.");
		        return "login";
			}
		}
		else {
			theModel.addAttribute("error", "Email is not registered. Please register and then login.");
	        return "login";
		}
	}
	
	@GetMapping("/addLesson")
	public String addLesson() {
		return "AddLesson";
	}
	
	@GetMapping("/createCourse")
	public String createCourse() {
		return "CreateCourse";
	}
	
	@GetMapping("/trainerHome")
	public String trianerHome() {
		return "TrainerHome";
	}
	
	@GetMapping("/courses")
	public String courses() {
		return "courses";
	}
	
}