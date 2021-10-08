package com.SafetyNet.Alert.Controller;
import com.SafetyNet.Alert.JsonReader;
import com.SafetyNet.Alert.DTO.*;
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
		public List<FireStationDTO> listOfPeopleServicedByFireStation(@RequestParam String station_Number) throws ParseException {
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
		public List<FloodStationHolderDTO> householdInStationJurisdiction (@RequestParam List<String> stations){
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
		@GetMapping("/person")
		public List<Persons> gettingPersons(){
			return json.listOfPersons() ;
			
		}
		@PostMapping("/person")
		public Persons addPerson(@RequestBody Persons person) {
			return fireStationsRepository.addingToListOfPersons(person);
		}
		@PutMapping("/person")
		public Persons updatePerson(@RequestParam String firstName, @RequestParam String lastName,
				@RequestBody Persons person){
			return fireStationsRepository.updatingListOfPersons(person);
		}

		
}
