package com.nagarro.javafreshertraining.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.javafreshertraining.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	/* Pagination Method */
	
	@Query("from Ticket as c where c.user.id =:userId")
	public Page<Ticket> findTicketsByUser(@Param("userId")int userId, Pageable pageable);
	
}
