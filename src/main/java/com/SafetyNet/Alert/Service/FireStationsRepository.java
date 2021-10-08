package com.SafetyNet.Alert.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.SafetyNet.Alert.DTO.*;
import com.SafetyNet.Alert.JsonReader;

import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FireStationsRepository {
	Logger logger = LoggerFactory.getLogger(FireStationsRepository.class);
	JsonReader jsonReader = new JsonReader();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 *
	 * @param station
	 * @return listOfPeopleServicedByFireStation with other details
	 */

	public List<FireStationDTO> listOFPeopleServicedByFireStation(String station) throws ParseException {
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		List<FireStationDTO> peopleDetailsInStation = new ArrayList<>();
		for(String address:getAddressByStationNumber(station)){
			FireStationDTO fireStationDTO = new FireStationDTO();
			for(Persons persons : jsonReader.listOfPersons()){
				if(persons.getAddress().contains(address))   {

						fireStationDTO.setFirstName(persons.getFirstName());
						fireStationDTO.setLastName(persons.getLastName());
						fireStationDTO.setPhoneNumber(persons.getPhone());
						fireStationDTO.setAddress(address);


					}

			}
			//fireStationDTO.setChildrenAgeSummary(gettingAgeSummaryFromMedicationAtAnAddressForChildren(address));
			//fireStationDTO.setAdultAgeSummary(gettingAgeSummaryFromMedicationAtAnAddressForAdult(address));
			peopleDetailsInStation.add(fireStationDTO);

		}

		logger.info("Response --" + new Gson().toJson(peopleDetailsInStation));
	return peopleDetailsInStation;

    }


	/**
	 *
	 * @param station
	 * @return addressesServicedByStation
	 */

	public List <String> getAddressByStationNumber(String station){
		List<String> addressesServicedByStation = new ArrayList<>();
		logger.info("returns address serviced by stationNumber");
		try {
			for(FireStations fireStationList : jsonReader.listOfFireStations()) {
				if(fireStationList.getStation().equals(station)) {
					addressesServicedByStation.add(fireStationList.getAddress());
				}
			}
		} catch (Exception e) {
			logger.error("an exception happened while trying to get address from station number" + e);
		}
		logger.info("addresses severviced by station are "+addressesServicedByStation);
		return addressesServicedByStation;
	}

	/**
	 *
	 * @param currentDate and birthDate
	 * @return ageDifference
	 */
	public String ageDifference(String currentDate , String birthDate) throws ParseException {
		Date date = new Date();
		currentDate = dateFormat.format(date);
	int ageDifference=	(dateFormat.parse(currentDate).getYear() )- (dateFormat.parse(birthDate).getYear());
	String ageDifferneceInStringFormat = Integer.toString(ageDifference);
		return  ageDifferneceInStringFormat;
	}

	/**
	 *
	 * @param address
	 * @return gets firstName and lastName of people leaving at address
	 */
		public List<NamesDTOAtAddress> namesOfPersonsLeavingInAnAddress(String address) {
			List<NamesDTOAtAddress> names = new ArrayList<>();
			try {

				for (Persons persons : jsonReader.listOfPersons()) {
					if (persons.getAddress().contains(address)) {
						NamesDTOAtAddress namesDTOAtAddress = new  NamesDTOAtAddress();
						namesDTOAtAddress.setFirstName(persons.getFirstName());
						namesDTOAtAddress.setLastName(persons.getLastName());
						names.add(namesDTOAtAddress);
					}
				}
			} catch (Exception e) {
					logger.error("an error was thrown so no name is displayed" +e);
			}
			return names;
		}

	/**
	 *
	 * @param address
	 * @return age Summary for children
	 */
	public String gettingAgeSummaryFromMedicationAtAnAddressForChildren(String address){
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String ageSummary=null;
		int count = 0;
		try {

			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
				for (NamesDTOAtAddress namesDTOAtAddress : namesOfPersonsLeavingInAnAddress(address)) {
					if ((medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName()))
							&& (medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName()))) {
						String dateOfPerson = medicalRecords.getBirthdate();
						logger.info(dateOfPerson);
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						logger.info(Integer.toString(age));
						if (age < 18) {
							count++;
						}
					}
				}
			}
		}
		catch (Exception e) {
				logger.error("an error occurred while trying to get age summary for children"+e);
		}

		return ageSummary;
	}

	/**
	 *
	 * @param address
	 * @return age summary for adult
	 */
	public String gettingAgeSummaryFromMedicationAtAnAddressForAdult(String address){
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String ageSummary=null;
		int count = 0;
		try {
			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
				for (NamesDTOAtAddress namesDTOAtAddress : namesOfPersonsLeavingInAnAddress(address)) {
					if ((medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName()))
							&& (medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName()))){
						String dateOfPerson = medicalRecords.getBirthdate();
						logger.info(dateOfPerson);
						int age = (dateFormat.parse(currentDate).getYear()-dateFormat.parse(dateOfPerson).getYear());
						logger.info(Integer.toString(age));
						if(age>18){
							count++;
						}
						ageSummary = Integer.toString(count);
					}
				}

			}

		}
		catch (ParseException e) {
			logger.error("an error occurred while trying to get age summary for adult"+e);
		}

		return ageSummary;
	}

	/**
	 *
	 * @param address
	 * @return the names and age of children living at address if only children live at the address
	 */
	public List<ChildAndAdultDTO> childAlertAPI(String address) {
		String adultLivingInAddress= null;
		ChildAndAdultDTO childAndAdultDTO = new ChildAndAdultDTO();
		List<ChildAndAdultDTO> listOfChildAndAdultDTODetails = new ArrayList<>();
		List<ChildAlertDTO> childrenLivingInAddress = new ArrayList<>();
		List<String> adultDetails = new ArrayList<>();
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		try {

			for (NamesDTOAtAddress namesDTOAtAddress : namesOfPersonsLeavingInAnAddress(address)) {
				ChildAlertDTO childAlertDTO = new ChildAlertDTO();

				for (MedicalRecords medicalRecords : jsonReader.listOfMedicalRecords()) {
					if (medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName())
							&& medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName())) {
						childAlertDTO.setFirstName(namesDTOAtAddress.getFirstName());
						childAlertDTO.setLastName(namesDTOAtAddress.getLastName());

						String dateOfPerson = medicalRecords.getBirthdate();
						logger.info(dateOfPerson);
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						logger.info(Integer.toString(age));
						childAlertDTO.setAge(Integer.toString(age));


					}
				}
				

				if(Integer.parseInt(childAlertDTO.getAge())<18) {
					childrenLivingInAddress.add(childAlertDTO);
				}else{
					adultLivingInAddress = "firstName "+childAlertDTO.getFirstName()+" "+" lastName "+childAlertDTO.getLastName();

				}
				adultDetails.add(adultLivingInAddress);
				logger.info("children list at address " + childrenLivingInAddress);
				logger.info("adultLivingInAddress list at address " + adultDetails);


				for(int i = 0; i<childrenLivingInAddress.size();i++){

					if(childrenLivingInAddress.size()==0){
						adultDetails.clear();
					}
				}

			}
			
			childAndAdultDTO.setChildrenAtAddress(childrenLivingInAddress);
			childAndAdultDTO.setAdultAtAddress(adultDetails);
			listOfChildAndAdultDTODetails.add(childAndAdultDTO);

		}

		catch (Exception e) {
			logger.error("an error occurred no child detail found"+e);
		}
		logger.info("Response --" + new Gson().toJson(listOfChildAndAdultDTODetails));
		return listOfChildAndAdultDTODetails;
	}

	/**
	 *
	 * @param station_Number
	 * @return the phone number of people services by the station number
	 */

	public List<PhoneAlertDTO> getPhoneNumberByAddress(String station_Number){
		List<PhoneAlertDTO> phoneAlertDTOList = new ArrayList<>();
		for(String address : getAddressByStationNumber(station_Number)){
			PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
			for(Persons persons : jsonReader.listOfPersons()){
				if(persons.getAddress().equals(address)){	
					phoneAlertDTO.setPhoneNumber(persons.getPhone());
				}
			}
			phoneAlertDTOList.add(phoneAlertDTO);
		}
		logger.info("Response --" + new Gson().toJson(phoneAlertDTOList));
		return phoneAlertDTOList;
	}


/**
 * 
 * @param address
 * @return string of station number
 */

	public String fireStationNumberFromAddress(String address){
		String fireStationNumber= null;
	try{
		for(FireStations fireStations : jsonReader.listOfFireStations()) {
			if(fireStations.getAddress().equals(address)) {
				fireStationNumber = fireStations.getStation();
			}
		}
		}catch (Exception e){
			logger.error("an error was thrown so no station number for that address" + e);
		}
		return fireStationNumber;
	}
/**
 * 
 * @param address
 * @return  fire station number that services the provided address, list of all of
the people living at the address. This include each person’s name, phone number, age,
medications, dosage, and allergies.
 */
		public List<FireAlertDTO> listOfPeopleServicedByStationNumberAfterGettingAddress(String address){
		List<FireAlertDTO> listOfPeopleServicedByStationNumber = new ArrayList<>();
			Date date = new Date();
			String currentDate = dateFormat.format(date);
		try {
			for(NamesDTOAtAddress namesDTOAtAddress : namesOfPersonsLeavingInAnAddress(address)){
			FireAlertDTO fireAlertDTO = new FireAlertDTO();
			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
				if((medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName()))
				&&(medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName()))){

					fireAlertDTO.setStationNumber(fireStationNumberFromAddress(address));
					fireAlertDTO.setFirstName(medicalRecords.getFirstName());
					fireAlertDTO.setLastName(medicalRecords.getLastName());
					String dateOfPerson = medicalRecords.getBirthdate();
					logger.info(dateOfPerson);
					int age = (dateFormat.parse(currentDate).getYear()-dateFormat.parse(dateOfPerson).getYear());
					logger.info(Integer.toString(age));
					fireAlertDTO.setAge(Integer.toString(age));
					fireAlertDTO.setAllergies(medicalRecords.getAllergies());
					fireAlertDTO.setMedications(medicalRecords.getMedications());


				}
			}
			listOfPeopleServicedByStationNumber.add(fireAlertDTO);
		}

		}catch(Exception e) {
			logger.error("an error occurred"+e);
		}
			logger.info("Response --" + new Gson().toJson(listOfPeopleServicedByStationNumber));
			return listOfPeopleServicedByStationNumber;
	}
		/**
		 * 
		 * @param list of stations  
		 * @return one station at a time
		 */

		public String gettingStationFromListOfStations(List<String> stations) {
			String station = null;
			for(String stationNumber: stations) {
				station = stationNumber;
			}
			return station;
		}
		/**
		 * 
		 * @param address
		 * @return details of people at an address
		 */
		public List<FloodStationDTO> houseHoldListOfPeople(String address){
			List<FloodStationDTO>listOfPeople = new ArrayList<>();
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			try {
				for (NamesDTOAtAddress namesDTOAtAddress :namesOfPersonsLeavingInAnAddress(address)) {
					FloodStationDTO floodStationDTO = new FloodStationDTO();
				for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
						if (namesDTOAtAddress.getFirstName().contains(medicalRecords.getFirstName())&&
						(namesDTOAtAddress.getLastName().contains(medicalRecords.getLastName()))){
							int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
									dateFormat.parse(medicalRecords.getBirthdate()).getYear());

							String ageDifferencesString = Integer.toString(ageDifferrences);
							floodStationDTO.setFirstName(medicalRecords.getFirstName());
							floodStationDTO.setLastName(medicalRecords.getLastName());
							floodStationDTO.setAge(ageDifferencesString);
							floodStationDTO.setAllergies(medicalRecords.getAllergies());
							floodStationDTO.setMedications(medicalRecords.getMedications());

						}
					}
				listOfPeople.add(floodStationDTO);
				break;
				}
			}catch (Exception e){
				logger.error("an error occured so no flood details was displayed" + e);
			}
			logger.info("Response --" + new Gson().toJson(listOfPeople));
			return listOfPeople;

		}

	public List<FloodStationHolderDTO> houseHoldInFireStationJurisdiction(List<String> stations){
		List<FloodStationHolderDTO> houseHolds = new ArrayList<>();
		for(String station : stations) {
		for(String address : getAddressByStationNumber(station)) {
			FloodStationHolderDTO floodStationHolderDTO = new FloodStationHolderDTO();
			floodStationHolderDTO.setPeopleAffected(houseHoldListOfPeople(address));
			houseHolds.add(floodStationHolderDTO);
		}
		}
		return houseHolds;
	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the email of the name of the individual provided
	 */
	public String gettingEmailsOfIndividuals(String firstName, String lastName) {
		String emailFromNamesOfPeople = null;
		try {
			for (Persons persons : jsonReader.listOfPersons()) {
				if ((persons.getFirstName().contains(firstName)) && persons.getLastName().contains(lastName)) {
					emailFromNamesOfPeople = persons.getEmail();
				}
			}
		}catch (Exception e){
		logger.error("an error was thrown so no email was returned" + e);
	}
		return emailFromNamesOfPeople;
	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the address of the name of the individual provided
	 */
	public String gettingAddressFromNames(String firstName , String lastName) {
		String addressFromNamesOfPeople = null;
		try{
		for(Persons persons : jsonReader.listOfPersons()) {
			if((persons.getFirstName().contains(firstName))&&persons.getLastName().contains(lastName)) {
				addressFromNamesOfPeople = persons.getAddress();
			}
		}}catch (Exception e){
			logger.error("an error was thrown so no address was returned" + e);
		}
		return addressFromNamesOfPeople;
	}
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return details about individual
	 */
	public List<PersonInfoDTO> personInfo(String firstName, String lastName){
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		List<PersonInfoDTO> personsInformation = new ArrayList<>();
		try {
			
			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
				
				if(medicalRecords.getFirstName().contains(firstName)&&
						medicalRecords.getLastName().contains(lastName))
				{
					PersonInfoDTO personInfoDTO = new PersonInfoDTO();
					int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
							dateFormat.parse(medicalRecords.getBirthdate()).getYear());
					String ageDifferencesString = Integer.toString(ageDifferrences);
					personInfoDTO.setFirstName(medicalRecords.getFirstName());
					personInfoDTO.setLastName(medicalRecords.getLastName());
					personInfoDTO.setAge(ageDifferencesString);
					personInfoDTO.setAddress(gettingAddressFromNames(firstName,lastName));
					personInfoDTO.setEmail(gettingEmailsOfIndividuals(firstName, lastName));
					personInfoDTO.setMedication(medicalRecords.getMedications());
					personInfoDTO.setAllergies(medicalRecords.getAllergies());
					personsInformation.add(personInfoDTO);
				}
				
			}
		}catch(Exception e) {
			logger.error("an error occurred therefore no person information at this time" + e);;
		}
		logger.info("Response --" + new Gson().toJson(personsInformation));
		return personsInformation;
	}
	public List<CommunityEmailDTO> gettingListOfEmailFromCity(String city){
		List <CommunityEmailDTO> emails = new ArrayList<>();
		try {
			for (Persons persons : jsonReader.listOfPersons()) {
				if (persons.getCity().contains(city)) {
					CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
					communityEmailDTO.setEmail(persons.getEmail());
					emails.add(communityEmailDTO);
				}
			}
		}catch(Exception e){
			logger.error("an error was thrown so no email was returned" +e);
		}
		logger.info("Response --" + new Gson().toJson(emails));
		return emails;

	}
	public Persons addingToListOfPersons(Persons person) {
			return person;
	}
	
	public Persons updatingListOfPersons(Persons person) {
		for(Persons persons: jsonReader.listOfPersons()) {
			if(person.getFirstName().equals(persons.getFirstName())&&person.getLastName().equals(persons.getLastName())){
				persons.setAddress(person.getAddress());
				persons.setCity(person.getCity());
				persons.setEmail(person.getEmail());
				persons.setPhone(person.getPhone());
				persons.setZip(person.getZip());
			}else{
				logger.info("person does not exist record cannot be updated");
			}
			return persons;
		}
		return null;
	}
	
}
