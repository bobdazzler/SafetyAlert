package com.safety.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.safety.JsonReader;
import com.safety.dto.ChildAndAdultDTO;
import com.safety.dto.CommunityEmailDTO;
import com.safety.dto.FireAlertDTO;
import com.safety.dto.FireStationDTOHolder;
import com.safety.dto.FloodStationHolderDTOAndAddress;
import com.safety.dto.PersonInfoDTO;
import com.safety.dto.PhoneAlertDTO;
import com.safety.model.MedicalRecords;
import com.safety.model.Persons;
import com.safety.service.SafetyAlertService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;

@RestController
public class AlertController {
	JsonReader reader = new JsonReader();
	Logger logger =  LoggerFactory.getLogger(AlertController.class);
	SafetyAlertService safetyAlertService;
	@Autowired
	public AlertController(SafetyAlertService safetyAlertService) {
		this.safetyAlertService = safetyAlertService;
	}
	/**
	 * 
	 * @return welcomes one to the site
	 */
	@RequestMapping("/")
	public String welcomeAlert() {
		return "Welcome to SafetyNet.";
	}
	/*
	 * get people based on station_number provided
	 * @param station_number
	 * @returns firstName, lastName, Address, PhoneNumber of people with age summary of people in the address
	 *
	 */
	@GetMapping("/firestations/stationNumber")
	@ResponseBody
	public List<FireStationDTOHolder> listOfPeopleServicedByFireStation(@RequestParam String station_Number) throws ParseException {
		logger.info("Get /firestations/stationNumber");
		return safetyAlertService.listOFPeopleServicedByFireStation(station_Number);
	}
	/**
	 * 
	 * @param address
	 * @return list of children (anyone under the age of 18) at the address provided. 
	 * The list include the first and last name of each child, the child’s age, 
	 * and a list of other persons living at that address. 
	 * If there are no children at the address, an empty string is returned.
	 */
	@GetMapping("/childAlert/address")
	@ResponseBody
	public List<ChildAndAdultDTO> childrenAtAddressServicedByFireStation(@RequestParam String address) {
		return safetyAlertService.childAlertAPI(address);
	}
	/**
	 * 
	 * @param station_Number
	 * @return a list of phone numbers of each person within the fire station’s jurisdiction.
	 */
	@GetMapping("/phoneAlert/firestation")
	@ResponseBody
	public List<PhoneAlertDTO> listOfPhoneNumbers(@RequestParam String station_Number){
		return safetyAlertService.getPhoneNumberByAddress(station_Number);
	}
	/**
	 * @param address
	 * @return fire station number that services the provided address as well as a list of all of the
	 *  people living at the address. This list include each person’s name, phone number, age,
	 *   medications with dosage, and allergies.
	 */
	@GetMapping("/fire/address")
	@ResponseBody
	public List<FireAlertDTO> medicals(@RequestParam String address){
		return safetyAlertService.listOfPeopleServicedByStationNumberAfterGettingAddress(address);
	}
	/**
	 * 
	 * @param stations
	 * @return a list of all the households in each fire station’s jurisdiction. 
	 * This list group people by household address, include name, phone number, and age of each person,
	 *  and any medications (with dosages) and allergies beside each person’s name.
	 */
	@GetMapping("/flood/stations")
	public List<FloodStationHolderDTOAndAddress> householdInStationJurisdiction (@RequestParam List <String> stations){
		return safetyAlertService.houseHoldInFireStationJurisdiction(stations);
	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the person’s name, address, age, email, list of medications with dosages and allergies.
	 *  If there is more than one person with the same name return all of them.
	 */
	@GetMapping("/personInfo")
	public List<PersonInfoDTO> personsInformation(@RequestParam String firstName,@RequestParam String lastName){
		return safetyAlertService.personInfo(firstName,lastName);
	}
	/**
	 * 
	 * @param city
	 * @return the email addresses of all of the people in the city
	 */
	@GetMapping("/communityEmail")
	public List<CommunityEmailDTO> communityEmail(@RequestParam String city){
		return safetyAlertService.gettingListOfEmailFromCity(city);
	}
	@GetMapping("/medical")
	public List<Persons> medic(){
		System.out.println(reader.listOfPersons().toString());
		return reader.listOfPersons();
	}
}
