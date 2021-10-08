package com.SafetyNet.Alert.DTO;

import java.util.List;

public class FloodStationHolderDTO {
	List<FloodStationDTO> peopleAffected;

	public List<FloodStationDTO> getPeopleAffected() {
		return peopleAffected;
	}

	public void setPeopleAffected(List<FloodStationDTO> peopleAffected) {
		this.peopleAffected = peopleAffected;
	}

	@Override
	public String toString() {
		return "FloodStationHolderDTO [peopleAffected=" + peopleAffected + "]";
	}
	

}
