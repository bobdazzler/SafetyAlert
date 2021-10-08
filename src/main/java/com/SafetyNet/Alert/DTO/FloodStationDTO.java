package com.SafetyNet.Alert.DTO;

import java.util.List;

public class FloodStationDTO {
	String firstName;
	String lastName;
	String age;
	
	List<String> allergies;
	List<String> medications;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	@Override
	public String toString() {
		return "FloodStationDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", allergies="
				+ allergies + ", medications=" + medications + "]";
	}
	
	
	

}
