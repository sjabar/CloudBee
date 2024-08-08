package com.travel.ticketbooking.api.repository;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Section {
	private String name;
	private Map<Integer, Ticket> seats = new HashMap<>();
	
	public Section() {
		
	}
	
	public Section(String name) {
		this.name = name;
		seats.put(1, null);
		seats.put(2, null);
		seats.put(3, null);
		seats.put(4, null);
		seats.put(5, null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, Ticket> getSeats() {
		return seats;
	}

	public void setSeats(Map<Integer, Ticket> seats) {
		this.seats = seats;
	}	
	
}
