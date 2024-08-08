package com.travel.ticketbooking.api.service;

import java.util.Iterator;
import java.util.Map;

import com.travel.ticketbooking.api.repository.Availablity;
import com.travel.ticketbooking.api.repository.Repository;
import com.travel.ticketbooking.api.repository.Section;
import com.travel.ticketbooking.api.repository.Ticket;
import com.travel.ticketbooking.api.repository.Train;

public class BookingService {
	public Availablity isTicketAvailable(Repository repository) {
		Availablity availablity = null;
		for (Train train: repository.getTrains()) {
			for(Section section: train.getSections()) {
				Iterator <Integer> it = ((Map<Integer, Ticket>)section.getSeats()).keySet().iterator();
				while(it.hasNext()) {
					int key=(int)it.next();
					if (section.getSeats().get(key) == null) {
						availablity = new Availablity(section.getName(), key, train.getTrainNumber(), true);
						break;
					}
				}
				if (availablity != null)
					break;
			}
		}
		
		return availablity;
	}
	
	public Availablity isTicketAvailable(Repository repository, Ticket ticket, String sectionName, Integer newSeatNumber) {
		Availablity availablity = null;
		for (Train train: repository.getTrains()) {
			for(Section section: train.getSections()) {
				if (section.getName().equals(sectionName)) {
					Map<Integer, Ticket> seats = section.getSeats();
					if(seats.get(newSeatNumber) == null) {
						availablity = new Availablity(section.getName(), newSeatNumber, train.getTrainNumber(), true);
						break;
					}
				}
			}
			if (availablity != null)
				break;
		}
		
		return availablity;
	}

}
