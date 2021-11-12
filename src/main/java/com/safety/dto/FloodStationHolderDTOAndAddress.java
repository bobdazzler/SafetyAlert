package com.safety.dto;
import java.util.List;
public class FloodStationHolderDTOAndAddress {
    List<FloodStationHolderDTO> PeopleAffected;

	public List<FloodStationHolderDTO> getPeopleAffected() {
		return PeopleAffected;
	}

	public void setPeopleAffected(List<FloodStationHolderDTO> peopleAffected) {
		PeopleAffected = peopleAffected;
	}
    
}
