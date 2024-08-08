package com.travel.ticketbooking.api.repository;

public class User {
	public User(String fname, String lname, String email) {
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
	}
	
	private String firstName;
	
	private String lastName;
	
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
