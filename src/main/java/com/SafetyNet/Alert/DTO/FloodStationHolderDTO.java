package com.SafetyNet.Alert.DTO;

import java.util.List;

public class FloodStationHolderDTO {
	String address;
	List<FloodStationDTO> peopleAffected;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<FloodStationDTO> getPeopleAffected() {
		return peopleAffected;
	}
	public void setPeopleAffected(List<FloodStationDTO> peopleAffected) {
		this.peopleAffected = peopleAffected;
	}
	
}
