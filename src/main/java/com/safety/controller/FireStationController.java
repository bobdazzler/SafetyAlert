package com.safety.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.safety.model.FireStations;
import com.safety.service.SafetyAlertService;
@RestController
public class FireStationController {
	SafetyAlertService safetyAlertService;
	@Autowired
	public FireStationController(SafetyAlertService safetyAlertService) {
		this.safetyAlertService = safetyAlertService;
	}
	/**
	 * 
	 * @param fireStation
	 * an object firestation is added to the json file
	 */
	@PostMapping("/fireStation")
	public void addFireStation(@RequestBody FireStations fireStation) {
		safetyAlertService.addingToListOfFireStations(fireStation);
	}
	/**
	 * 
	 * @param fireStation
	 * @param address
	 * an update is carried out on the firestation object if the address provided are the same. 
	 */
	@PutMapping("/fireStation")
	public void updateFireStation(@RequestBody FireStations fireStation, @RequestParam String address) {
		safetyAlertService.updatingListOfFireStation(fireStation,address);
	}
	@DeleteMapping("/fireStation")
	public void deleteFireStation(@RequestBody FireStations fireStation ) {
		safetyAlertService.deletingFromListOFFireStation(fireStation);
	}
}
