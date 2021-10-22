package com.SafetyNet.Alert.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.SafetyNet.Alert.DTO.*;
import com.SafetyNet.Alert.JsonReader;

import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;
import com.SafetyNet.Alert.SafetyNetData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FireStationsRepository {
	Logger logger = LoggerFactory.getLogger(FireStationsRepository.class);
	JsonReader jsonReader = new JsonReader();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * @param station
	 * @return listOfPeopleServicedByFireStation with other details
	 */

	public List<FireStationDTOHolder> listOFPeopleServicedByFireStation(String station) {
		Date date = new Date();
		int childrenCount = 0;
		int adultCount = 0;
		String currentDate = dateFormat.format(date);
		List<FireStationDTOHolder> finalDetailsOfPeopleInStation = new ArrayList<>();
		List<FireStationDTO> peopleDetailsInStation = new ArrayList<>();
		List<Persons> persons = jsonReader.listOfPersons();
		try {
			for (String address : getAddressByStationNumber(station)) {
				for (Persons person : persons) {
					if (person.getAddress().contains(address)) {
						FireStationDTO fireStationDTO = new FireStationDTO();
						fireStationDTO.setFirstName(person.getFirstName());
						fireStationDTO.setLastName(person.getLastName());
						fireStationDTO.setPhoneNumber(person.getPhone());
						fireStationDTO.setAddress(address);
						for (MedicalRecords medicalRecords : jsonReader.listOfMedicalRecords()) {
							if (medicalRecords.getFirstName().contains(person.getFirstName()) &&
									medicalRecords.getLastName().contains(person.getLastName())) {
								String dateOfPerson = medicalRecords.getBirthdate();
								int ageDifference = (dateFormat.parse(currentDate).getYear()) -
										(dateFormat.parse(dateOfPerson).getYear());
								String ageDifferneceInStringFormat = Integer.toString(ageDifference);
								fireStationDTO.setAge(ageDifferneceInStringFormat);

							}
						}
						if (Integer.parseInt(fireStationDTO.getAge()) < 18) {
							childrenCount++;
						} else {
							adultCount++;
						}
						peopleDetailsInStation.add(fireStationDTO);

					}

				}


			}
			FireStationDTOHolder fireStationDTOHolder = new FireStationDTOHolder();
			fireStationDTOHolder.setFireStationDTOSHolder(peopleDetailsInStation);
			fireStationDTOHolder.setAgeSummaryForChildren(Integer.toString(childrenCount));
			fireStationDTOHolder.setAgeSummaryForAdult(Integer.toString(adultCount));
			finalDetailsOfPeopleInStation.add(fireStationDTOHolder);
		} catch (Exception e) {
			logger.error("an error was thrown so list of people serviced by station number is not returned " + e);
		}

		logger.info("Response --" + new Gson().toJson(finalDetailsOfPeopleInStation));
		return finalDetailsOfPeopleInStation;

	}


	/**
	 * @param station
	 * @return addressesServicedByStation
	 */

	public List<String> getAddressByStationNumber(String station) {
		List<String> addressesServicedByStation = new ArrayList<>();
		logger.info("returns address serviced by stationNumber");
		try {
			for (FireStations fireStationList : jsonReader.listOfFireStations()) {
				if (fireStationList.getStation().equals(station)) {
					addressesServicedByStation.add(fireStationList.getAddress());
				}
			}
		} catch (Exception e) {
			logger.error("an exception happened while trying to get address from station number" + e);
		}
		logger.info("addresses severviced by station are " + addressesServicedByStation);
		return addressesServicedByStation;
	}

	/**
	 * @param address
	 * @return gets firstName and lastName of people leaving at address
	 */
	public List<NamesDTOAtAddress> namesOfPersonsLeavingInAnAddress(String address) {
		List<NamesDTOAtAddress> names = new ArrayList<>();
		try {

			for (Persons persons : jsonReader.listOfPersons()) {
				if (persons.getAddress().contains(address)) {
					NamesDTOAtAddress namesDTOAtAddress = new NamesDTOAtAddress();
					namesDTOAtAddress.setFirstName(persons.getFirstName());
					namesDTOAtAddress.setLastName(persons.getLastName());
					names.add(namesDTOAtAddress);
				}
			}
		} catch (Exception e) {
			logger.error("an error was thrown so no name is displayed" + e);
		}
		logger.info("names of people living at address " + names);
		return names;
	}

	/**
	 * @param address
	 * @return the names and age of children living at address if only children live at the address
	 */
	public List<ChildAndAdultDTO> childAlertAPI(String address) {
		String adultLivingInAddress = null;
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


				if (Integer.parseInt(childAlertDTO.getAge()) < 18) {
					childrenLivingInAddress.add(childAlertDTO);
				} else {
					adultLivingInAddress = "firstName " + childAlertDTO.getFirstName() + " " + " lastName " + childAlertDTO.getLastName();
					adultDetails.add(adultLivingInAddress);
				}

				logger.info("children list at address " + childrenLivingInAddress);
				logger.info("adultLivingInAddress list at address " + adultDetails);


				for (int i = 0; i < childrenLivingInAddress.size(); i++) {

					if (childrenLivingInAddress.size() == 0) {
						adultDetails.clear();
					}
				}

			}

			childAndAdultDTO.setChildrenAtAddress(childrenLivingInAddress);
			childAndAdultDTO.setAdultAtAddress(adultDetails);
			listOfChildAndAdultDTODetails.add(childAndAdultDTO);

		} catch (Exception e) {
			logger.error("an error occurred no child detail found" + e);
		}
		logger.info("Response --" + new Gson().toJson(listOfChildAndAdultDTODetails));
		return listOfChildAndAdultDTODetails;
	}

	/**
	 * @param station_Number
	 * @return the phone number of people services by the station number
	 */

	public List<PhoneAlertDTO> getPhoneNumberByAddress(String station_Number) {
		List<PhoneAlertDTO> phoneAlertDTOList = new ArrayList<>();
		for (String address : getAddressByStationNumber(station_Number)) {
			PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
			for (Persons persons : jsonReader.listOfPersons()) {
				if (persons.getAddress().equals(address)) {

					phoneAlertDTO.setPhoneNumber(persons.getPhone());

				}
			}
			phoneAlertDTOList.add(phoneAlertDTO);
		}
		logger.info("Response --" + new Gson().toJson(phoneAlertDTOList));
		return phoneAlertDTOList;
	}


	/**
	 * @param address
	 * @return string of station number
	 */

	public String fireStationNumberFromAddress(String address) {
		String fireStationNumber = null;
		try {
			for (FireStations fireStations : jsonReader.listOfFireStations()) {
				if (fireStations.getAddress().equals(address)) {
					fireStationNumber = fireStations.getStation();
				}
			}
		} catch (Exception e) {
			logger.error("an error was thrown so no station number for that address" + e);
		}
		return fireStationNumber;
	}

	/**
	 * @param address
	 * @return fire station number that services the provided address, list of all of
	 * the people living at the address. This include each person’s name, phone number, age,
	 * medication and allergies.
	 */
	public List<FireAlertDTO> listOfPeopleServicedByStationNumberAfterGettingAddress(String address) {
		List<FireAlertDTO> listOfPeopleServicedByStationNumber = new ArrayList<>();
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		try {
			for (NamesDTOAtAddress namesDTOAtAddress : namesOfPersonsLeavingInAnAddress(address)) {	
				for (MedicalRecords medicalRecords : jsonReader.listOfMedicalRecords()) {
					if ((medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName()))
							&& (medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName()))) {
						FireAlertDTO fireAlertDTO = new FireAlertDTO();
						fireAlertDTO.setStationNumber(fireStationNumberFromAddress(address));
						fireAlertDTO.setFirstName(medicalRecords.getFirstName());
						fireAlertDTO.setLastName(medicalRecords.getLastName());
						String dateOfPerson = medicalRecords.getBirthdate();
						logger.info(dateOfPerson);
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						logger.info(Integer.toString(age));
						fireAlertDTO.setAge(Integer.toString(age));
						fireAlertDTO.setAllergies(medicalRecords.getAllergies());
						fireAlertDTO.setMedications(medicalRecords.getMedications());
						listOfPeopleServicedByStationNumber.add(fireAlertDTO);
						break;
					}

				}


			}

		} catch (Exception e) {
			logger.error("an error occurred" + e);
		}
		logger.info("Response --" + new Gson().toJson(listOfPeopleServicedByStationNumber));
		return listOfPeopleServicedByStationNumber;
	}

	/**
	 * @param address
	 * @return details of people at an address
	 */
	public List<FloodStationHolderDTO> houseHoldListOfPeople(String address) {
		List<FloodStationHolderDTO> houseHolds = new ArrayList<>();
		List<FloodStationDTO> listOfPeople = new ArrayList<>();
		List<Persons> persons = jsonReader.listOfPersons();
		List<MedicalRecords> medicalRecords = jsonReader.listOfMedicalRecords();
		try {
			FloodStationHolderDTO floodStationHolderDTO = new FloodStationHolderDTO();
			Date date = new Date();
			String currentDate = dateFormat.format(date);	
					for(Persons person:persons) {
						if(person.getAddress().equals(address)) {
							floodStationHolderDTO.setAddress(address);	
							FloodStationDTO floodStationDTO =new FloodStationDTO();
							floodStationDTO.setFirstName(person.getFirstName());
							floodStationDTO.setLastName(person.getLastName());
							floodStationDTO.setPhoneNumber(person.getPhone());
							for(MedicalRecords medicalRecord: medicalRecords) {
								if (medicalRecord.getFirstName().equals(person.getFirstName()) &&
										(medicalRecord.getLastName().equals(person.getLastName()))) {
									int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
											dateFormat.parse(medicalRecord.getBirthdate()).getYear());
									String ageDifferencesString = Integer.toString(ageDifferrences);
									floodStationDTO.setAge(ageDifferencesString);
									floodStationDTO.setAllergies(medicalRecord.getAllergies());
									floodStationDTO.setMedications(medicalRecord.getMedications());
									continue;
								}
							}
							listOfPeople.add(floodStationDTO);
						}
					}	
					floodStationHolderDTO.setPeopleAffected(listOfPeople);
					houseHolds.add(floodStationHolderDTO);
		} catch (Exception e) {
			logger.error("an error occured so no flood details was displayed" + e);
		}
		logger.info("Response --" + new Gson().toJson(listOfPeople));
		return houseHolds;

	}
	public List<FloodStationHolderDTOAndAddress> houseHoldInFireStationJurisdiction(List<String>stations) {
		List<FloodStationHolderDTOAndAddress> stationHolderDTOAndAddresses = new ArrayList<>();
		
		try {
			for(String station : stations) {	
				
				for (String address : getAddressByStationNumber(station)) {
					FloodStationHolderDTOAndAddress stationHolderDTOAndAddress = 
							new FloodStationHolderDTOAndAddress();
					stationHolderDTOAndAddress.setPeopleAffected(houseHoldListOfPeople(address));
					stationHolderDTOAndAddresses.add(stationHolderDTOAndAddress);
				}
				
			}	
		} catch (Exception e) {
			logger.error("an error occured so no flood details was displayed" + e);
		}
		logger.info("Response --" + new Gson().toJson(stationHolderDTOAndAddresses));
		return stationHolderDTOAndAddresses;
	}

	/**
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
		} catch (Exception e) {
			logger.error("an error was thrown so no email was returned" + e);
		}
		return emailFromNamesOfPeople;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @return the address of the name of the individual provided
	 */
	public String gettingAddressFromNames(String firstName, String lastName) {
		String addressFromNamesOfPeople = null;
		try {
			for (Persons persons : jsonReader.listOfPersons()) {
				if ((persons.getFirstName().contains(firstName)) && persons.getLastName().contains(lastName)) {
					addressFromNamesOfPeople = persons.getAddress();
				}
			}
		} catch (Exception e) {
			logger.error("an error was thrown so no address was returned" + e);
		}
		return addressFromNamesOfPeople;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @return details about individual
	 */
	public List<PersonInfoDTO> personInfo(String firstName, String lastName) {
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		List<PersonInfoDTO> personsInformation = new ArrayList<>();
		try {

			for (MedicalRecords medicalRecords : jsonReader.listOfMedicalRecords()) {

				if (medicalRecords.getFirstName().contains(firstName) &&
						medicalRecords.getLastName().contains(lastName)) {
					PersonInfoDTO personInfoDTO = new PersonInfoDTO();
					int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
							dateFormat.parse(medicalRecords.getBirthdate()).getYear());
					String ageDifferencesString = Integer.toString(ageDifferrences);
					personInfoDTO.setFirstName(medicalRecords.getFirstName());
					personInfoDTO.setLastName(medicalRecords.getLastName());
					personInfoDTO.setAge(ageDifferencesString);
					personInfoDTO.setAddress(gettingAddressFromNames(firstName, lastName));
					personInfoDTO.setEmail(gettingEmailsOfIndividuals(firstName, lastName));
					personInfoDTO.setMedication(medicalRecords.getMedications());
					personInfoDTO.setAllergies(medicalRecords.getAllergies());
					personsInformation.add(personInfoDTO);
				}

			}
		} catch (Exception e) {
			logger.error("an error occurred therefore no person information at this time" + e);
			;
		}
		logger.info("Response --" + new Gson().toJson(personsInformation));
		return personsInformation;
	}

	/**
	 * @param city
	 * @return all the individual email addresses in the city
	 */
	public List<CommunityEmailDTO> gettingListOfEmailFromCity(String city) {
		List<CommunityEmailDTO> emails = new ArrayList<>();
		try {
			for (Persons persons : jsonReader.listOfPersons()) {
				if (persons.getCity().contains(city)) {
					CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
					communityEmailDTO.setEmail(persons.getEmail());
					emails.add(communityEmailDTO);
				}
			}
		} catch (Exception e) {
			logger.error("an error was thrown so no email was returned" + e);
		}
		logger.info("Response --" + new Gson().toJson(emails));
		return emails;

	}

	public void addingToListOfPersons(Persons person) {
		String nameRead;
		try {
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(new FileReader("src/main/resources/data.json"));
			JsonObject jsonObject = (JsonObject) obj;
			JsonArray msg = (JsonArray)jsonObject.get("persons");
			Iterator<JsonElement> iterator = msg.iterator();
			while(iterator.hasNext()) {
				nameRead = iterator.next().toString();
				System.out.println(nameRead);
			}
			Persons persons = new Persons();
			persons.setFirstName(person.getFirstName());
			persons.setLastName(person.getLastName());
			persons.setAddress(person.getAddress());
			persons.setEmail(person.getEmail());
			persons.setPhone(person.getPhone());
			persons.setCity(person.getCity());
			persons.setZip(person.getZip());
			Gson gson = new Gson();
			gson.toJson(persons);

			FileWriter file = new FileWriter("src/main/resources/data.json",false);
			JsonWriter jw = new JsonWriter(file);
			iterator = msg.iterator();
			SafetyNetData emps = new SafetyNetData();
			while(iterator.hasNext()) {
				emps.setPersons(gson.fromJson(iterator.next().toString(), Persons.class));
			}
			emps.setPersons(persons);
			gson.toJson(emps, SafetyNetData.class, jw);
			file.close();

		}catch(FileNotFoundException fFE) {
			logger.error("file not found error "+ fFE);
		} catch (IOException e) {
			logger.error("an input and out put error was thrown "+e);
		}

	}
	public void updatingListOfPersons(Persons person, String firstName, String lastName) {
		for (Persons persons : jsonReader.listOfPersons()) {
			if (firstName.equals(persons.getFirstName()) && lastName.equals(persons.getLastName())) {
				persons.setAddress(person.getAddress());
				persons.setCity(person.getCity());
				persons.setEmail(person.getEmail());
				persons.setPhone(person.getPhone());
				persons.setZip(person.getZip());
				return;
			} else {
				logger.info("person does not exist record cannot be updated");
			}
		}
	}
}
