package com.doranco.coursSpring.model.service;

public class AuthService {

	public boolean login(String login, String password) {
		return login.equals("Robert") && password.equals("1234");
	}
	
	public boolean logout() {
		return true;
	}
	
	public boolean isLogged() {
		return false;
	}
	
	public boolean register(String login, String password) {
		return true;
	}
	
}
