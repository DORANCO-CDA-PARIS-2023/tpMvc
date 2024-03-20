package com.doranco.coursSpring.enums;

public enum UserRoles {

	ADMIN("admin"),
	MODERATOR("moderator"),
	USER("user");
	
	private String role;
	
	private UserRoles(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	
	
	
}
