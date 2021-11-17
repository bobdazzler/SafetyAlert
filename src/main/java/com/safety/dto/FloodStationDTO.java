package com.safety.dto;
import java.util.List;
public class FloodStationDTO {
	
	String firstName;
	String lastName;
	String phoneNumber;
	String age;
	List<String> allergies;
	List<String> medications;
	public FloodStationDTO() {
	}
	public FloodStationDTO(String firstName, String lastName, String phoneNumber, String age, List<String> allergies,
			List<String> medications) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.allergies = allergies;
		this.medications = medications;
	}
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return "FloodStationDTO [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", age=" + age + ", allergies=" + allergies + ", medications=" + medications + "]";
	}
}
