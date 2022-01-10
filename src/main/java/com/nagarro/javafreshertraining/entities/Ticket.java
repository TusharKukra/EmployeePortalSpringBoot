package com.nagarro.javafreshertraining.entities;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ticketId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description" ,length=1000)
	private String description;
	
	/* Here also we do mapping to get the user from Ticket (Bi-directional Mapping) */
	
	@ManyToOne()
	private User user;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
}
