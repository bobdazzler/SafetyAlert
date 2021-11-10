package com.SafetyNet.Alert.DTO;

import java.util.List;

public class PersonInfoDTO {
String firstName;
String lastName;
String age;
String address;
String email;
List<String> medication;
List <String> allergies;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public List<String> getMedication() {
	return medication;
}
public void setMedication(List<String> medication) {
	this.medication = medication;
}
public List<String> getAllergies() {
	return allergies;
}
public void setAllergies(List<String> allergies) {
	this.allergies = allergies;
}
@Override
public String toString() {
	return "PersonInfoDTO [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address
			+ ", email=" + email + ", medication=" + medication + ", allergies=" + allergies + "]";
}

}
