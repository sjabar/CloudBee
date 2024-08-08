package com.travel.ticketbooking.api.repository;

public class Availablity {	
	private String sectionName;
	private int seatNumber;
	private int trainNumber;
	boolean availabile = false;
	
	public Availablity(String sectionName, int seatNumber, int trainNumber, boolean availabile) {
		super();
		this.sectionName = sectionName;
		this.seatNumber = seatNumber;
		this.trainNumber = trainNumber;
		this.availabile = availabile;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isAvailabile() {
		return availabile;
	}

	public void setAvailabile(boolean availabile) {
		this.availabile = availabile;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}
	
	
}
