package com.travel.ticketbooking.api.repository;

import java.util.List;

public class Train {
	private String trainName;
	private int trainNumber;
	private List<Section> sections;
	
	public Train(String trainName, int trainNumber, List<Section> sections) {
		super();
		this.trainName = trainName;
		this.trainNumber = trainNumber;
		this.sections = sections;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
