package com.safety.dto;
import java.util.List;
public class FireAlertDTO {
    String stationNumber;
    String firstName;
    String lastName;
    String age;
    List<String>medications;
    List<String>allergies;
    public FireAlertDTO(String stationNumber, String firstName, String lastName, String age, List<String> medications,
			List<String> allergies) {
		this.stationNumber = stationNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}
	public FireAlertDTO() {
	}
	
    public String getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
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
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public List<String> getMedications() {
        return medications;
    }
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
    public List<String> getAllergies() {
        return allergies;
    }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
   
}
