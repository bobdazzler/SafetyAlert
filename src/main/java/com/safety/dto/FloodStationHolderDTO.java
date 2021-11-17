package com.safety.dto;
import java.util.List;
public class FloodStationHolderDTO {
	String address;
	List<FloodStationDTO> peopleAffected;
	
	public FloodStationHolderDTO(String address, List<FloodStationDTO> peopleAffected) {
		this.address = address;
		this.peopleAffected = peopleAffected;
	}
	public FloodStationHolderDTO() {
	}
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
