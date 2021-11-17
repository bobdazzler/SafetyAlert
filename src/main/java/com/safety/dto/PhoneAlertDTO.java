package com.safety.dto;
public class PhoneAlertDTO {
    String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public PhoneAlertDTO(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public PhoneAlertDTO() {
	}
	@Override
    public String toString() {
        return "PhoneAlertDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
