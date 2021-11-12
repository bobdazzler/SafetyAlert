package com.safety.dto;
public class CommunityEmailDTO {
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "CommunityEmailDTO [email=" + email + "]";
	}
}
