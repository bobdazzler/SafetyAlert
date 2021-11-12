package com.safety.dto;
public class ChildAlertDTO {
	String firstName;
	String lastName;
	String age;
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
	@Override
	public String toString() {
		return "ChildAlertDTO{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", ChildrenAge='" + age + '\'' +
				'}';
	}
}
