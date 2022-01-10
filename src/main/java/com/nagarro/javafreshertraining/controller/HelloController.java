package com.nagarro.javafreshertraining.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nagarro.javafreshertraining.dao.UserRepository;
import com.nagarro.javafreshertraining.entities.Ticket;
import com.nagarro.javafreshertraining.entities.User;
import com.nagarro.javafreshertraining.helper.Message;

@Controller
public class HelloController {
	
	// Autowiring password encoder
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Autowiring UserRepository
	
	@Autowired
	private UserRepository userRepository;
	
	/* Home page handler */
	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home - Employee Portal");
		return "home";
	}
	
	/* About page handler */
	@RequestMapping("/about")
	public String about(Model m) {
		m.addAttribute("title", "About - Employee Portal");
		return "about";
	}
	
	
	/* Signup page handler */
	@RequestMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("title", "Register - Employee Portal");
		m.addAttribute("user",new User());
		return "signup"; 
	}
	
	/* Handler for Register User */
	@RequestMapping(value="/do_register", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user,BindingResult result1, Model m, HttpSession session) {
		
		try {
			
			// Validation 
			if(result1.hasErrors()) {
				System.out.println("Error"+result1.toString());
				m.addAttribute("user",user);
				return "signup";
			}
			
			// Set role
			user.setRole("ROLE_USER");
			
			// user enter password and the BCryptEncoder encrypts it and set in user. So, we get Encoded Password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			// Using toString method in User.java
			System.out.println("USER "+user);
			
			// UserRepo
			User result = this.userRepository.save(user);
			
			// Adding data to Model
			m.addAttribute("user",new User());
			
			// If everything is good then send Success Message
			session.setAttribute("message", new Message("Successfully Registerd","alert-success"));
			
			return "signup";
			
			
		} catch(Exception e) {
			// Handle exception
			e.printStackTrace();
			m.addAttribute("user",user);
			
			// If everything is not good then send Error Message
			session.setAttribute("message", new Message("Something went Wrong!!!"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		
		 
	}
	
	/* Login page handler */
	@GetMapping("/signin")
	public String login(Model m) {
		m.addAttribute("title", "Login - Employee Portal");
		return "login";
	}
	
	
}
