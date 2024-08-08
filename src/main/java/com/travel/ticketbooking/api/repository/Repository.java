package com.travel.ticketbooking.api.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Repository {
	List<Ticket> tickets = new ArrayList<Ticket>();
	List<Train> trains = new ArrayList<Train>();
	List<User> users = new ArrayList<User>();
	public Repository() {
		List<Section> sections = new ArrayList<Section>();
		sections.add(new Section("Section A"));
		sections.add(new Section("Section B"));
		trains.add(new Train("London Express", 1007, sections));
	}
	
	public List<Train> getTrains() {
		return trains;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User newUser) {
		boolean isFound = false;
		for(User existingUser: this.users) {
			if (newUser.getFirstName().equalsIgnoreCase(existingUser.getFirstName())
					&& newUser.getLastName().equalsIgnoreCase(existingUser.getLastName())
					&& newUser.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
				isFound = true;
			}
		}
		
		if (!isFound) {
			this.users.add(newUser);
		}
	}

	public void addTicket(Ticket ticket) {
		for(Train train: this.trains) {
			for(Section section: train.getSections()) {
				if (section.getName().equals(ticket.getSectionName())) {
					Map<Integer, Ticket> seats = section.getSeats();
					seats.put(ticket.getSeatNumber(), ticket);
				}
			}
		}
		this.tickets.add(ticket);
	}
	
	public List<Ticket> deleteTicket(int ticketNumber) {
		Ticket deletedTicket = null;
		for(int i=this.trains.size()-1; i>=0; i--) {
			Ticket ticket = this.tickets.get(i);
			if(ticketNumber == ticket.getTicketNumber()) {
				deletedTicket = this.tickets.remove(i);
			}
		}
		
		if (deletedTicket != null) {
			for(Train train: this.trains) {
				if (train.getTrainNumber() == deletedTicket.getTrainNumber()) {
					for(Section section: train.getSections()) {
						if (section.getName().equals(deletedTicket.getSectionName())) {
							Map<Integer, Ticket> seats = section.getSeats();
							seats.put(deletedTicket.getSeatNumber(), null);
						}
					}
				}
				
			}
		}
		return this.tickets;
		
	}
	
	public Ticket updateTicket(Ticket newTicket) {
		int ticketNumber = newTicket.getTicketNumber();
		for(int i=0; i<this.tickets.size(); i++) {
			Ticket ticket = this.tickets.get(i);
			if (ticket.getTicketNumber() == newTicket.getTicketNumber()) {
				this.tickets.set(i, newTicket);
				break;
			}
		}
		
		for(Train train: this.trains) {
			for(Section section: train.getSections()) {
				Iterator <Integer> it = ((Map<Integer, Ticket>)section.getSeats()).keySet().iterator();
				while(it.hasNext()) {
					int key=(int)it.next();
					if (section.getSeats().get(key) != null) {
						Ticket ticket = section.getSeats().get(key);
						if(ticket.getTicketNumber() == newTicket.getTicketNumber()) {
							section.getSeats().put(key, null);
						}
					}
				}
			}
		}
		
		if (newTicket != null) {
			for(Train train: this.trains) {
				if (train.getTrainNumber() == newTicket.getTrainNumber()) {
					for(Section section: train.getSections()) {
						if (section.getName().equals(newTicket.getSectionName())) {
							Map<Integer, Ticket> seats = section.getSeats();
							seats.put(newTicket.getSeatNumber(), newTicket);
						}
					}
				}
				
			}
		}
		
		return newTicket;
	}
	
	public int getTicketNumber() {
		return 500 + this.tickets.size() + 1;
	}
	
	public Ticket getTicket(int ticketNumber) {
		Ticket ticketSelected = null;
		for(Ticket ticket: this.tickets) {
			if (ticket.getTicketNumber() == ticketNumber) {
				ticketSelected = ticket;
			}
		}
		return ticketSelected;
	}
	
	
}
