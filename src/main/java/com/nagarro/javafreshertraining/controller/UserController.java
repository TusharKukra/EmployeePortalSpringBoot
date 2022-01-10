package com.nagarro.javafreshertraining.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.javafreshertraining.dao.TicketRepository;
import com.nagarro.javafreshertraining.dao.UserRepository;
import com.nagarro.javafreshertraining.entities.Ticket;
import com.nagarro.javafreshertraining.entities.User;
import com.nagarro.javafreshertraining.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@ModelAttribute
	public void addNameOnAllPage(Model m, Principal principal) {
		
		String userName = principal.getName();
		System.out.println("Username: "+userName);
		
		// Get the User using Username(Email)
		User user = userRepository.getUserByUserName(userName);
	
		System.out.println("User: "+user);
		
		// Sending data of user
		m.addAttribute("user",user);
		
	}
	
	/* DashBoard Home Page */
	
	@RequestMapping("/index")
	public String dashboard(Model m, Principal principal) {
		
		m.addAttribute("title","User Dashboard");
		return "normal-user/user_dashboard";
	}
	
	
	/* Add Ticket Handler */
	
	@GetMapping("/add-ticket")
	public String openAddTicketForm(Model m) {
		
		m.addAttribute("title","Add Ticket");
		m.addAttribute("ticket", new Ticket());
		return "normal-user/add_ticket_form";
	}
	
	
	/* Process Ticket Handler */
	
	@PostMapping("/process-ticket")
	public String processTicket(@ModelAttribute Ticket ticket, Principal principal, HttpSession session) {
		
		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);
			
			ticket.setUser(user);
		
			user.getTickets().add(ticket);
					
			// Saving ticket to a particular user
			this.userRepository.save(user);
			
			System.out.println("Added to Database");
			
			System.out.println("Ticket: "+ticket);
			
			//Success Message
			session.setAttribute("message", new Message("Your Ticket is Successfully Added !", "success"));
			
			

		} catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
			
			//Error Message
			session.setAttribute("message", new Message("Something went wrong, Try Again !!", "danger"));
		}
		
		
		return "normal-user/add_ticket_form";
	}
	
	
	/* Show Tickets Handler (per page 7 tickets) */
	
	@GetMapping("/show-tickets/{page}")
	public String showTickets(@PathVariable("page") Integer page, Model m, Principal principal) {
		
		m.addAttribute("title","Show Ticket");
		
		// Get username by Principal
		String userName = principal.getName();
		
		User user = this.userRepository.getUserByUserName(userName);
		
		// Showing Ticket list of particular user
		Pageable pageable = PageRequest.of(page, 8);
		Page<Ticket> tickets = this.ticketRepository.findTicketsByUser(user.getId(),pageable);
		
		m.addAttribute("tickets", tickets);
		
		m.addAttribute("currentPage",page);
		
		m.addAttribute("totalPages", tickets.getTotalPages());
		
		return "normal-user/show_tickets";
	}
	
	
	/* Delete Ticket Handler */
	
	@GetMapping("/delete/{ticketId}")
	public String deleteTicket(@PathVariable("ticketId") Integer ticketId, Model m, Principal principal, HttpSession session) {
		
		Optional<Ticket> ticketOptional = this.ticketRepository.findById(ticketId);

		Ticket ticket = ticketOptional.get();
		
		
		
		// Now check whether the person login is deleting his/her tickets only
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		if(user.getId() == ticket.getUser().getId()) {
			
			// Unlink user from Ticket (to delete)
			ticket.setUser(null);
			
			// then only he/she can delete his/her ticket
			this.ticketRepository.delete(ticket);
			
			session.setAttribute("message", new Message("Ticket Deleted Successfully" , "success"));
			
		}
		return "redirect:/user/show-tickets/0";
	}
	
	
	
	/* Edit Ticket Handler */
	 
	@PostMapping("/update-ticket/{ticketId}")
	public String editTicket(@PathVariable("ticketId") Integer ticketId,Model m) {
		
		m.addAttribute("title","Edit Ticket");
		
		Ticket ticket = this.ticketRepository.findById(ticketId).get();
		
		m.addAttribute("ticket",ticket);
		
		return "normal-user/update_form";
	}
	
	
	/* Update Edited Ticket Handler */
	
	@RequestMapping(value="/process-update", method= RequestMethod.POST)
	public String updateHandler(@ModelAttribute Ticket ticket, Model model, Principal principal, HttpSession session) {
		
		try {
			User user = this.userRepository.getUserByUserName(principal.getName());
			ticket.setUser(user);
			this.ticketRepository.save(ticket);
			session.setAttribute("message", new Message("Ticket is Updated Successfully!","success"));
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went Wrong!!!","danger"));
		}
		return "redirect:/user/show-tickets/0";
	}
	
}
