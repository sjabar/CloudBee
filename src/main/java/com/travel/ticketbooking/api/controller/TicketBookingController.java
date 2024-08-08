package com.travel.ticketbooking.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.ticketbooking.api.service.BookingService;
import com.travel.ticketbooking.api.repository.Availablity;
import com.travel.ticketbooking.api.repository.Repository;
import com.travel.ticketbooking.api.repository.ResponseMessage;
import com.travel.ticketbooking.api.repository.Ticket;
import com.travel.ticketbooking.api.repository.Train;

@RestController
public class TicketBookingController {
	
	private Repository repository = new Repository();
	private BookingService bookingService = new BookingService();
	
	@GetMapping("/trains")
	public List<Train> getTrains() {
		return this.repository.getTrains();
	}
	
	@PostMapping("/book")
	public Ticket bookTicket(@RequestBody Ticket ticket) {
		ResponseMessage response = new ResponseMessage();
		this.repository.addUser(ticket.getUser());
		Availablity availablity = bookingService.isTicketAvailable(this.repository);
		if (availablity != null) {
			int seatNumber = availablity.getSeatNumber();
			String sectionName = availablity.getSectionName();
			int trainNumber = availablity.getTrainNumber();
			ticket.setSeatNumber(seatNumber);
			ticket.setSectionName(sectionName);
			ticket.setTrainNumber(trainNumber);
			ticket.setTicketNumber(this.repository.getTicketNumber());
			response.setMessage("Seat Number " + seatNumber + " is allocation in train " + trainNumber + " - compartment " + sectionName);
			ticket.setResponse(response);
			this.repository.addTicket(ticket);
		} else {
			response.setError("Seats Not Available");
		}
		return ticket;
	}
	
	
	@DeleteMapping("/ticket")
	public List<Ticket> deleteTicket(@RequestParam("ticketNumber") int ticketNumber) {
		return this.repository.deleteTicket(ticketNumber);
	}
	
	@PutMapping("/ticket")
	public Ticket updateTicket(@RequestParam int ticketNumber, @RequestParam String sectionName, @RequestParam int seatNumber) {
		ResponseMessage response = new ResponseMessage();
		Ticket ticket = this.repository.getTicket(ticketNumber);
		Availablity availablity = bookingService.isTicketAvailable(repository, ticket, sectionName, seatNumber);
		if (availablity != null) {
			int trainNumber = availablity.getTrainNumber();
			ticket.setSeatNumber(seatNumber);
			ticket.setSectionName(sectionName);
			ticket.setTrainNumber(trainNumber);
			ticket.setTicketNumber(repository.getTicketNumber());
			response.setMessage("Seat " + seatNumber + " is available in train " + trainNumber + " section " + sectionName);
			ticket.setResponse(response);
			this.repository.updateTicket(ticket);
		} else {
			response.setError("Seats Not Available");
		}
		return ticket;
	}

}
