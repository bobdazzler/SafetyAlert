package com.safety.dto;
public class NamesDTOAtAddress {
    String firstName;
    String lastName;
    
    public NamesDTOAtAddress(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public NamesDTOAtAddress() {
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
    @Override
    public String toString() {
        return "NamesDTOAtAddress{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
