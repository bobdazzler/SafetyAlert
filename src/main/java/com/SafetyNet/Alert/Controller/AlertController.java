package com.SafetyNet.Alert.Controller;
import com.SafetyNet.Alert.JsonReader;
import com.SafetyNet.Alert.DTO.*;
import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;
import com.SafetyNet.Alert.Service.FireStationsRepository;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;

@RestController
public class AlertController {


	Logger logger =  LoggerFactory.getLogger(AlertController.class);
	FireStationsRepository fireStationsRepository = new FireStationsRepository();
	JsonReader json = new JsonReader();
	//welcomes one to the site
	@RequestMapping("/")
	public String welcomeAlert() {
		return "Welcome to SafetyNet.";
	}


	/*
	 * get people based on station_number provided
	 * @param station_number
	 * @returns firstName, lastName, Address, PhoneNumber of people with age summary of people in the address
	 */

	@GetMapping("/firestations/stationNumber")
	@ResponseBody
	public List<FireStationDTOHolder> listOfPeopleServicedByFireStation(@RequestParam String station_Number) throws ParseException {
		logger.info("Get /firestations/stationNumber");
		return fireStationsRepository.listOFPeopleServicedByFireStation(station_Number);
	}

	@GetMapping("/childAlert/address")
	@ResponseBody
	public List<ChildAndAdultDTO> childrenAtAddressServicedByFireStation(@RequestParam String address) {

		return fireStationsRepository.childAlertAPI(address);
	}
	@GetMapping("/phoneAlert/firestation")
	@ResponseBody
	public List<PhoneAlertDTO> listOfPhoneNumbers(@RequestParam String station_Number){
		return fireStationsRepository.getPhoneNumberByAddress(station_Number);
	}

	@GetMapping("/fire/address")
	@ResponseBody
	public List<FireAlertDTO> medicals(@RequestParam String address){
		return fireStationsRepository.listOfPeopleServicedByStationNumberAfterGettingAddress(address);
	}
	@GetMapping("/flood/stations")
	public List<FloodStationHolderDTOAndAddress> householdInStationJurisdiction (@RequestParam List <String> stations){
		return fireStationsRepository.houseHoldInFireStationJurisdiction(stations);
	}
	@GetMapping("/personInfo")
	public List<PersonInfoDTO> personsInformation(@RequestParam String firstName,@RequestParam String lastName){
		return fireStationsRepository.personInfo(firstName,lastName);

	}
	@GetMapping("/communityEmail")
	public List<CommunityEmailDTO> communityEmail(@RequestParam String city){
		return fireStationsRepository.gettingListOfEmailFromCity(city);
	}
	@PostMapping("/person")
	public void addPerson(@RequestBody Persons person) {
		fireStationsRepository.addingToListOfPersons(person);
	}
	@PutMapping("/person")
	public void updatePerson(@RequestBody Persons person){
		fireStationsRepository.updatingListOfPersons(person);
	}
	@DeleteMapping("/person")
	public void deletePerson(@RequestBody Persons person ) {
		fireStationsRepository.deletingFromListOfPersons(person);
	}
	@PostMapping("/fireStation")
	public void addFireStation(@RequestBody FireStations fireStation) {
		fireStationsRepository.addingToListOfFireStations(fireStation);
	}
	@PutMapping("/fireStation")
	public void updateFireStation(@RequestBody FireStations fireStation, @RequestParam String address) {
		fireStationsRepository.updatingListOfFireStation(fireStation,address);
	}
	@DeleteMapping("/fireStation")
	public void deleteFireStation(@RequestBody FireStations fireStation ) {
		fireStationsRepository.deletingFromListOFFireStation(fireStation);
	}
	@PostMapping("/medicalRecords")
	public void addMedicalRecords(@RequestBody MedicalRecords records) {
		fireStationsRepository.addingToMedicalRecords(records);
	}
	@PutMapping("/medicalRecords")
	public void updateMedicalRecords(@RequestBody MedicalRecords records) {
		fireStationsRepository.updatingMedicalRecords(records);
	}
	@DeleteMapping("/medicalRecords")
	public void deletingRecordFromMedicalRecords(@RequestBody MedicalRecords records) {
		fireStationsRepository.deletingRecordsFromMedicalRecords(records);
	}
}
