package com.SafetyNet.Alert.DTO;

public class FireStationDTO {
	String firstName;
	String lastName;
	String address;
	String phoneNumber;
	String AdultAgeSummary;
	String ChildrenAgeSummary;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdultAgeSummary() {
		return AdultAgeSummary;
	}
	public void setAdultAgeSummary(String adultAgeSummary) {
		AdultAgeSummary = adultAgeSummary;
	}
	public String getChildrenAgeSummary() {
		return ChildrenAgeSummary;
	}
	public void setChildrenAgeSummary(String childrenAgeSummary) {
		ChildrenAgeSummary = childrenAgeSummary;
	}
	@Override
	public String toString() {
		return "FireStationDTO [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", AdultAgeSummary=" + AdultAgeSummary + ", ChildrenAgeSummary="
				+ ChildrenAgeSummary + "]";
	}
}
