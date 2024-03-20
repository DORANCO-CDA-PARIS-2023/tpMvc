package com.doranco.coursSpring.model.entity;

public class User {

	private int id;
	private String lastName;
	private String firstName;
	private String userName;
	private String password;
	private String email;

	public User() {
	}

	public User(int id, String lastName, String firstName, String userName, String password, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public User(String lastName, String firstName, String userName, String password, String email) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [lastName=" + lastName + ", firstName=" + firstName + ", userName=" + userName + ", email=" + email
				+ "]";
	}

}
