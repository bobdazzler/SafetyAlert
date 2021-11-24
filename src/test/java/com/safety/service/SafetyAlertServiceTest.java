package com.safety.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safety.JsonReader;
import com.safety.SafetyNetData;
import com.safety.dto.ChildAlertDTO;
import com.safety.dto.ChildAndAdultDTO;
import com.safety.dto.CommunityEmailDTO;
import com.safety.dto.FireAlertDTO;
import com.safety.dto.FireStationDTO;
import com.safety.dto.FireStationDTOHolder;
import com.safety.dto.FloodStationDTO;
import com.safety.dto.FloodStationHolderDTO;
import com.safety.dto.FloodStationHolderDTOAndAddress;
import com.safety.dto.NamesDTOAtAddress;
import com.safety.dto.PersonInfoDTO;
import com.safety.dto.PhoneAlertDTO;
import com.safety.model.FireStations;
import com.safety.model.MedicalRecords;
import com.safety.model.Persons;
@RunWith(SpringRunner.class)

public class SafetyAlertServiceTest {
	  SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Logger logger = LoggerFactory.getLogger(SafetyAlertServiceTest.class);
	@InjectMocks
	SafetyAlertService safetyAlertService;
	@Mock
	JsonReader jsonReader;
	@Mock
	SafetyNetData safetyMock;
	@Mock
	FileWriter fileWriter;
	@Mock
	File file;
	@Mock
	ArrayNode personsArray;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	@Test
	public void testListOFPeopleServicedByFireStation() {
		//arrange

		FireStationDTO fireStation = new FireStationDTO();
		fireStation.setFirstName("Oghoro");
		fireStation.setLastName("Bob");
		fireStation.setAddress("asaba");
		fireStation.setAge("45");
		List<FireStationDTO> listofFireStation = new ArrayList<>();
		listofFireStation.add(fireStation);
		FireStationDTOHolder holder = new FireStationDTOHolder(listofFireStation,"0","1");
		List<FireStationDTOHolder> listHolder = new ArrayList<>();
		listHolder.add(holder);
		when(safetyAlertService.listOFPeopleServicedByFireStation("1")).thenReturn(listHolder);
		List<FireStationDTOHolder> methodUnderTest = safetyAlertService.listOFPeopleServicedByFireStation("1");
		for(FireStationDTOHolder single : methodUnderTest) {
			assertThat(single.getAgeSummaryForAdult().equals("1"));
		}

	}
	@Test
	public void testListOfPeopleServicedByFireStrationfull() {
		List<FireStationDTO> peopleDetailsInStation = new ArrayList<>();
		List<FireStationDTOHolder> finalDetailsOfPeopleInStation = new ArrayList<>();
		int childrenCount =0;
		int adultCount=0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		Persons persons1 = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"); 
		Persons persons3 = new Persons("Roger","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		mockedPersons.add(persons3);
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		List<String> allergies1 = new ArrayList<>();
		List<String> allergies2 = new ArrayList<>();
		List<String> medications2 = new ArrayList<>();
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords("Jacob","Boyd","03/06/1989",medications1, allergies1);
		MedicalRecords record3 = new MedicalRecords("Roger","Boyd","09/06/2018",medications2, allergies2);
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		recordMocked.add(record2);
		recordMocked.add(record3);
		FireStations fireStation= new FireStations("1509 Culver St","1");
		List<FireStations> fireStationMocked = new ArrayList<>();
		fireStationMocked.add(fireStation);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		when(jsonReader.listOfFireStations()).thenReturn(fireStationMocked);
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);

		List<String> addresses= safetyAlertService.getAddressByStationNumber("1");
		System.out.println(addresses);
		try {
			for(Persons per :mockedPersons) {
				if(per.getAddress().equals(addresses)) {
					FireStationDTO fireStationDTO = new FireStationDTO();
					fireStationDTO.setAddress(per.getAddress());
					fireStationDTO.setFirstName(per.getFirstName());
					fireStationDTO.setLastName(per.getLastName());
					fireStationDTO.setPhoneNumber(per.getPhone());
					for(MedicalRecords med : recordMocked) {
						if(med.getFirstName().equals(per.getFirstName())&&med.getLastName().equals(per.getLastName())){
							String dateOfPerson = med.getBirthdate();
							int ageDifference = (dateFormat.parse(currentDate).getYear()) -
									(dateFormat.parse(dateOfPerson).getYear());
							String ageDifferneceInStringFormat = Integer.toString(ageDifference);
							fireStationDTO.setAge(ageDifferneceInStringFormat);}
					}

					if (Integer.parseInt(fireStationDTO.getAge()) < 18) {
						childrenCount++;
					} else {

						adultCount++;
					}

					peopleDetailsInStation.add(fireStationDTO);
				}
			}		
			FireStationDTOHolder fireStationDTOHolder = new FireStationDTOHolder();
			fireStationDTOHolder.setFireStationDTOSHolder(peopleDetailsInStation);
			fireStationDTOHolder.setAgeSummaryForChildren(Integer.toString(childrenCount));
			System.out.println(childrenCount);
			fireStationDTOHolder.setAgeSummaryForAdult(Integer.toString(adultCount));
			finalDetailsOfPeopleInStation.add(fireStationDTOHolder);

		}catch(Exception e) {
			logger.error("issue with mocking"+e);

		}
		List<FireStationDTOHolder> methodUnderTest = safetyAlertService.listOFPeopleServicedByFireStation("1");
		//assert
		for(FireStationDTOHolder fireStationHolder : methodUnderTest) {
			assertThat(fireStationHolder.getAgeSummaryForAdult().equals("2"));
			assertThat(fireStationHolder.getAgeSummaryForChildren().equals("1"));
		}
	}
	@Test
	public void  testListOfPeopleServicedByFireStrationException(){
			Persons persons1 = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
			Persons persons2 = new Persons("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"); 
			Persons persons3 = new Persons("Roger","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
			List<Persons> mockedPersons =new ArrayList<>();
			mockedPersons.add(persons2);
			mockedPersons.add(persons1);
			mockedPersons.add(persons3);
			String medi = "aznol:350mg"+"hydrapermazol:100mg";
			List<String> medications = new ArrayList<>();
			medications.add(medi);
			String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
			List<String> medications1 = new ArrayList<>();
			medications1.add(med1);
			String all = "nillacilan";
			List<String> allergies = new ArrayList<>();
			allergies.add(all);
			List<String> allergies1 = new ArrayList<>();
			List<String> allergies2 = new ArrayList<>();
			List<String> medications2 = new ArrayList<>();
			MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
			MedicalRecords record2 = new MedicalRecords("Jacob","Boyd","03/06/1989",medications1, allergies1);
			MedicalRecords record3 = new MedicalRecords("Roger","Boyd","90",medications2, allergies2);
			List<MedicalRecords> recordMocked = new ArrayList<>();
			recordMocked.add(record1);
			recordMocked.add(record2);
			recordMocked.add(record3);
			FireStations fireStation= new FireStations("1509 Culver St","1");
			List<FireStations> fireStationMocked = new ArrayList<>();
			fireStationMocked.add(fireStation);
			when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
			when(jsonReader.listOfFireStations()).thenReturn(fireStationMocked);
			for(int i =0; i<jsonReader.listOfMedicalRecords().size();i++) {
			when(jsonReader.listOfMedicalRecords().get(i).getBirthdate()).thenThrow(new Exception());
			}
			List<FireStationDTOHolder> methodUnderTest = safetyAlertService.listOFPeopleServicedByFireStation("1");
			for(FireStationDTOHolder hold : methodUnderTest) {
				hold.getAgeSummaryForAdult();
				hold.getAgeSummaryForChildren();
			}

			 exception.expect(Exception.class);
			   // exception.expectMessage("an error was thrown so list of people serviced by station number is not returned ");
		
	}
	@Test
	public void  getAddressByStationNumberWhenwrongStationIsInputed() {
		try {
		  safetyAlertService.getAddressByStationNumber("10");
		}catch(IllegalArgumentException ex) {
		assertEquals("station does not exist make sure the station exist",ex.getMessage());
		}
    }

	@Test
	public void testChildAlertAPI() {
		//arrange
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<ChildAndAdultDTO> listOfChildAndAdultDTODetailsMocked = new ArrayList<>();
		String currentDate = dateFormat.format(date);
		String adultLivingInAddress = null;
		List<String> adultDetailsMocked = new ArrayList<>();
		List<ChildAlertDTO> childrenListMocked = new ArrayList<>();
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		List<String> allergies1 = new ArrayList<>();
		MedicalRecords record1 = new MedicalRecords("Tega","Oghoro","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords("Nerovwo","Oghoro","03/06/2017",medications1, allergies1);
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		recordMocked.add(record2);
		Persons persons1 = new Persons("Tega","Oghoro","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Nerovwo","Oghoro","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		//act
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		List<NamesDTOAtAddress> listOfNamesAtAddressMocked = safetyAlertService.namesOfPersonsLeavingInAnAddress("asaba");
		System.out.println(listOfNamesAtAddressMocked);

		try {
			for(NamesDTOAtAddress names: listOfNamesAtAddressMocked) {
				ChildAlertDTO child = new ChildAlertDTO();
				for(MedicalRecords med : recordMocked) {
					if(names.getFirstName().equals(med.getFirstName()) && names.getLastName().equals(med.getLastName())){
						child.setFirstName(names.getFirstName());
						child.setLastName(names.getLastName());
						String dateOfPerson = med.getBirthdate();
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						child.setAge(age);
					}
				}
				if (child.getAge() < 18) {
					childrenListMocked.add(child);
				} else {
					adultLivingInAddress = "firstName " + child.getFirstName() + " " + " lastName " + child.getLastName();
					adultDetailsMocked.add(adultLivingInAddress);
				}
			}
			ChildAndAdultDTO childAndAdultDTO = new ChildAndAdultDTO();
			childAndAdultDTO.setChildrenAtAddress(childrenListMocked);
			childAndAdultDTO.setAdultAtAddress(adultDetailsMocked);
			listOfChildAndAdultDTODetailsMocked.add(childAndAdultDTO);
		}catch(Exception e) {
			logger.error("an error occured "+ e);
		}
		//assert
		List<ChildAndAdultDTO> methodUnderTest = safetyAlertService.childAlertAPI("asaba");
		for(ChildAndAdultDTO cAADTO:methodUnderTest)
			assertThat(cAADTO.getAdultAtAddress().equals(adultDetailsMocked));
	}
	@Test
	public void testChildAlertAPIWhenNoChildIsInAddress() {
		//arrange
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		List<ChildAndAdultDTO> listOfChildAndAdultDTODetailsMocked = new ArrayList<>();
		String currentDate = dateFormat.format(date);
		String adultLivingInAddress = null;
		List<String> adultDetailsMocked = new ArrayList<>();
		List<ChildAlertDTO> childrenListMocked = new ArrayList<>();
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		MedicalRecords record1 = new MedicalRecords("Tega","Oghoro","03/06/1984",medications,allergies );
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		Persons persons1 = new Persons("Tega","Oghoro","asaba","Culver","97451","841-874-6512","jaboyd@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons1);
		//act
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		List<NamesDTOAtAddress> listOfNamesAtAddressMocked = safetyAlertService.namesOfPersonsLeavingInAnAddress("asaba");
		System.out.println(listOfNamesAtAddressMocked);

		try {
			for(NamesDTOAtAddress names: listOfNamesAtAddressMocked) {
				ChildAlertDTO child = new ChildAlertDTO();
				for(MedicalRecords med : recordMocked) {
					if(names.getFirstName().equals(med.getFirstName()) && names.getLastName().equals(med.getLastName())){
						child.setFirstName(names.getFirstName());
						child.setLastName(names.getLastName());
						String dateOfPerson = med.getBirthdate();
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						child.setAge(age);
					}
				}
				if (child.getAge() < 18) {
					childrenListMocked.add(child);
				} else {
					adultLivingInAddress = "firstName " + child.getFirstName() + " " + " lastName " + child.getLastName();
					adultDetailsMocked.add(adultLivingInAddress);
				}
			}
			ChildAndAdultDTO childAndAdultDTO = new ChildAndAdultDTO();
			childAndAdultDTO.setChildrenAtAddress(childrenListMocked);
			childAndAdultDTO.setAdultAtAddress(adultDetailsMocked);
			listOfChildAndAdultDTODetailsMocked.add(childAndAdultDTO);

			for (ChildAndAdultDTO childAndAdult : listOfChildAndAdultDTODetailsMocked) {
				if (childAndAdult.getChildrenAtAddress().size() == 0) {
					adultDetailsMocked.clear();
				}
			}
		}catch(Exception e) {
			logger.error("an error occured "+ e);
		}
		//assert
		List<ChildAndAdultDTO> methodUnderTest = safetyAlertService.childAlertAPI("asaba");
		for(ChildAndAdultDTO cAADTO:methodUnderTest)
			assertThat(cAADTO.getAdultAtAddress().isEmpty());
	}

	@Test
	public void testGetPhoneNumberByAddress() {
		List<PhoneAlertDTO> phoneAlertDTOList = new ArrayList<>();
		Persons persons1 = new Persons("Tega","Oghoro","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Nerovwo","Oghoro","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		FireStations fireStation= new FireStations("asaba","1");
		List<FireStations> fireStationMocked = new ArrayList<>();
		fireStationMocked.add(fireStation);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		when(jsonReader.listOfFireStations()).thenReturn(fireStationMocked);
		List<String> address =safetyAlertService. getAddressByStationNumber("1");
		for(Persons pers: mockedPersons) {
			PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
			if(pers.getAddress().equals(address)) {
				phoneAlertDTO.setPhoneNumber(pers.getPhone());
				phoneAlertDTOList.add(phoneAlertDTO);
			}

		}
		List<PhoneAlertDTO> methodUnderTest = safetyAlertService.getPhoneNumberByAddress("1");
		for(PhoneAlertDTO phone : methodUnderTest) {
			assertThat(phone.getPhoneNumber().equals(persons1.getPhone()));
		}
	}


	@Test
	public void testListOfPeopleServicedByStationNumberAfterGettingAddress() {
		List<FireAlertDTO> listOfPeopleServicedByStationNumber = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		Persons persons1 = new Persons("John","Boyd","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		List<String> allergies1 = new ArrayList<>();
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords("Jacob","Boyd","03/06/1989",medications1, allergies1);
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		recordMocked.add(record2);
		FireStations fireStation= new FireStations("asaba","5");
		List<FireStations> fireStationMocked = new ArrayList<>();
		fireStationMocked.add(fireStation);
		when(jsonReader.listOfFireStations()).thenReturn(fireStationMocked);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		try {
			List<NamesDTOAtAddress> listOfNamesAtAddressMocked = safetyAlertService.namesOfPersonsLeavingInAnAddress("asaba");
			for (NamesDTOAtAddress namesDTOAtAddress : listOfNamesAtAddressMocked) { 
				for (MedicalRecords medicalRecords : recordMocked) {
					if ((medicalRecords.getFirstName().equals(namesDTOAtAddress.getFirstName()))
							&& (medicalRecords.getLastName().equals(namesDTOAtAddress.getLastName()))) {
						FireAlertDTO fireAlertDTO = new FireAlertDTO();
						fireAlertDTO.setStationNumber(safetyAlertService.fireStationNumberFromAddress("asaba"));
						fireAlertDTO.setFirstName(medicalRecords.getFirstName());
						fireAlertDTO.setLastName(medicalRecords.getLastName());
						String dateOfPerson = medicalRecords.getBirthdate();
						int age = (dateFormat.parse(currentDate).getYear() - dateFormat.parse(dateOfPerson).getYear());
						fireAlertDTO.setAge(Integer.toString(age));
						fireAlertDTO.setAllergies(medicalRecords.getAllergies());
						fireAlertDTO.setMedications(medicalRecords.getMedications());
						listOfPeopleServicedByStationNumber.add(fireAlertDTO);
					}
				}
			}
		}catch(Exception e) {
			logger.error("something happened"+e);
		}
		List<FireAlertDTO> methodUnderTest = safetyAlertService.listOfPeopleServicedByStationNumberAfterGettingAddress("asaba");
		for(FireAlertDTO fireAlert : methodUnderTest) {
			assertThat(fireAlert.getStationNumber().equals(5));
		}
	}

	@Test
	public void testHouseHoldListOfPeople() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		List<FloodStationHolderDTO> houseHolds = new ArrayList<>();
		List<FloodStationDTO> listOfPeople = new ArrayList<>();
		FloodStationHolderDTO floodStationHolderDTO = new FloodStationHolderDTO();
		Persons persons1 = new Persons("John","Boyd","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		List<String> allergies1 = new ArrayList<>();
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords("Jacob","Boyd","03/06/1989",medications1, allergies1);
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		recordMocked.add(record2);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		try {
			for(Persons person:mockedPersons) {
				if(person.getAddress().equals("asaba")) {
					floodStationHolderDTO.setAddress("asaba");  
					FloodStationDTO floodStationDTO =new FloodStationDTO();
					floodStationDTO.setFirstName(person.getFirstName());
					floodStationDTO.setLastName(person.getLastName());
					floodStationDTO.setPhoneNumber(person.getPhone());
					for(MedicalRecords medicalRecord: recordMocked) {
						if (medicalRecord.getFirstName().equals(person.getFirstName()) &&
								(medicalRecord.getLastName().equals(person.getLastName()))) {
							int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
									dateFormat.parse(medicalRecord.getBirthdate()).getYear());
							String ageDifferencesString = Integer.toString(ageDifferrences);
							floodStationDTO.setAge(ageDifferencesString);
							floodStationDTO.setAllergies(medicalRecord.getAllergies());
							floodStationDTO.setMedications(medicalRecord.getMedications());
						}
					}
					listOfPeople.add(floodStationDTO);
				}
			}
			floodStationHolderDTO.setPeopleAffected(listOfPeople);
			houseHolds.add(floodStationHolderDTO);
		}catch(Exception e) {
			logger.error("something happended "+ e);
		}
		List<FloodStationHolderDTO> methodUnderTest = safetyAlertService.houseHoldListOfPeople("asaba");
		for(FloodStationHolderDTO flood : methodUnderTest){
			assertThat(flood.getAddress().equals("asaba"));
		}
	}

	@Test
	public void testHouseHoldInFireStationJurisdiction() {
		List<FloodStationHolderDTOAndAddress> stationHolderDTOAndAddresses = new ArrayList<>();
		FireStations fireStation= new FireStations("asaba","1");
		List<FireStations> fireStationMocked = new ArrayList<>();
		fireStationMocked.add(fireStation);
		when(jsonReader.listOfFireStations()).thenReturn(fireStationMocked);
		List<String> stations = Arrays.asList("1");
		for(String station : stations) {    
			for (String address :safetyAlertService.getAddressByStationNumber(station)) {
				FloodStationHolderDTOAndAddress stationHolderDTOAndAddress = 
						new FloodStationHolderDTOAndAddress();
				stationHolderDTOAndAddress.setPeopleAffected(safetyAlertService.houseHoldListOfPeople(address));
				stationHolderDTOAndAddresses.add(stationHolderDTOAndAddress);
			}
		}
		List<FloodStationHolderDTOAndAddress> methodUnderTest = safetyAlertService.houseHoldInFireStationJurisdiction(stations);
		for(FloodStationHolderDTOAndAddress floodStation : methodUnderTest) {
			assertThat( !floodStation.getPeopleAffected().isEmpty());
		}
	}


	@Test
	public void testPersonInfo() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		Persons persons1 = new Persons("John","Boyd","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String med1 = "pharmacol:5000mg"+ "terazine:10mg"+ "noznazol:250mg";
		List<String> medications1 = new ArrayList<>();
		medications1.add(med1);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		List<String> allergies1 = new ArrayList<>();
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords("Jacob","Boyd","03/06/1989",medications1, allergies1);
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record1);
		recordMocked.add(record2);
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		String firstName =persons1.getFirstName();
		String lastName = persons1.getLastName();
		List<PersonInfoDTO> personsInformation = new ArrayList<>();
		try {

			for (MedicalRecords medicalRecords :recordMocked) {

				if (medicalRecords.getFirstName().contains(firstName) &&
						medicalRecords.getLastName().contains(lastName)) {
					PersonInfoDTO personInfoDTO = new PersonInfoDTO();
					int ageDifferrences = ((dateFormat.parse(currentDate).getYear()) -
							dateFormat.parse(medicalRecords.getBirthdate()).getYear());
					String ageDifferencesString = Integer.toString(ageDifferrences);
					personInfoDTO.setFirstName(medicalRecords.getFirstName());
					personInfoDTO.setLastName(medicalRecords.getLastName());
					personInfoDTO.setAge(ageDifferencesString);
					personInfoDTO.setAddress(safetyAlertService.gettingAddressFromNames(firstName, lastName));
					personInfoDTO.setEmail(safetyAlertService.gettingEmailsOfIndividuals(firstName, lastName));
					personInfoDTO.setMedication(medicalRecords.getMedications());
					personInfoDTO.setAllergies(medicalRecords.getAllergies());
					personsInformation.add(personInfoDTO);
				}
			}
		} catch (Exception e) {
			logger.error("an error occurred therefore no person information at this time" + e);
		}
		List<PersonInfoDTO> methodUnderTest = safetyAlertService.personInfo(firstName, lastName);
		for(PersonInfoDTO personsInfo: methodUnderTest) {
			assertThat(personsInfo.getFirstName().equals(firstName));
			assertThat(personsInfo.getEmail().equals("jaboyd@email.com"));
		}
	}

	@Test
	public void testGettingListOfEmailFromCity() {
		List<CommunityEmailDTO> emails = new ArrayList<>();
		Persons persons1 = new Persons("John","Boyd","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","asaba","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		try {
			for (Persons persons : mockedPersons) {
				if (persons.getCity().contains("Culver")) {
					CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
					communityEmailDTO.setEmail(persons.getEmail());
					emails.add(communityEmailDTO);

				}
			}
		} catch (Exception e) {
			logger.error("an error was thrown so no email was returned" + e);
		}
		List<CommunityEmailDTO> methodUnderTest = safetyAlertService.gettingListOfEmailFromCity("Culver");
		for(CommunityEmailDTO email : methodUnderTest) {
			assertThat(email.getEmail().equals("jaboyd@email.com"));
		}

	}

	@Test
	public void testAddingToListOfPersonsReadingFromFile() throws JsonProcessingException, IOException {
		Persons persons1 = new Persons("John","Oghoro","asaba","Culver","97451","841-874-6512","jaboyd@email.com");
		ObjectNode root = null;
		root= safetyAlertService.addingToListOfPersons(persons1);
		assertThat(root.findValues("firstName").equals(persons1.getFirstName()));
	}

	@Test
	public void testUpdatingListOfPersons() throws JsonProcessingException, IOException {
		Persons persons = new Persons("John","Boyd","asaba","Culver1","97451","841-874-6512","oghorod@email.com");
	//	Persons persons2 = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		ObjectNode per = null;
		per= safetyAlertService.updatingListOfPersons(persons);
		
		System.out.println(per);
		 ArrayNode personsArray = (ArrayNode) per.get("persons");
		for(int i = 0; i <personsArray.size();i++) {
		assertEquals(persons.getFirstName(),personsArray.get(i).get("firstName").asText());
		assertEquals(persons.getLastName(),personsArray.get(i).get("lastName").asText());
		assertEquals(persons.getAddress(),personsArray.get(i).get("address").asText());
		assertEquals(persons.getCity(),personsArray.get(i).get("city").asText());
		assertEquals(persons.getEmail(),personsArray.get(i).get("email").asText());
		assertEquals(persons.getPhone(),personsArray.get(i).get("phone").asText());
		assertEquals(persons.getZip(),personsArray.get(i).get("zip").asText());
		}
	}

	@Test
	public void testDeletingFromListOfPersons() {
		Persons persons1 = new Persons("Jacob","Boyd","asaba","Culver","97451","841-874-6512","oghorod@email.com");
		ObjectNode root = null;
		root= safetyAlertService.deletingFromListOfPersons(persons1);
		assertThat(root.findValues("firstName").contains("Jacob")&&root.findValues("lastName").contains("Boyd"));
	}

	@Test
	public void testAddingToListOfFireStations() {
		FireStations fireStation= new FireStations("asaba","5");
		ObjectNode root = null;
		root= safetyAlertService.addingToListOfFireStations(fireStation);
		assertThat(root.findValues("address").contains("asaba"));
	}

	@Test
	public void testUpdatingListOfFireStation() {
		FireStations fireStation= new FireStations("asaba","5");
		ObjectNode root = null;
		root= safetyAlertService.updatingListOfFireStation(fireStation,"908 73rd St");
		assertThat(root.findValues("address").contains("asaba"));
	}

	@Test
	public void testDeletingFromListOFFireStation() {
		FireStations fireStation= new FireStations("asaba","5");
		ObjectNode root = null;
		root= safetyAlertService.deletingFromListOFFireStation(fireStation);
		assertThat(root.findValues("address").contains("asaba"));
	}

	@Test
	public void testAddingToMedicalRecords() {
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String all = "nillacilan";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		MedicalRecords record1 = new MedicalRecords("Oghoro","John","03/06/1984",medications,allergies );
		ObjectNode root = null;
		root= safetyAlertService.addingToMedicalRecords(record1);
		assertThat(root.findValues("firstName").contains("Oghoro"));
	}

	@Test
	public void testUpdatingMedicalRecords() {
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String all = "nillacilanUpdated";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/2000",medications,allergies );
		ObjectNode root = null;
		root= safetyAlertService.updatingMedicalRecords(record1);
		assertThat(root.findValues("birthdate").contains("03/06/2000"));
	}

	@Test
	public void testDeletingRecordsFromMedicalRecords() {
		String medi = "aznol:350mg"+"hydrapermazol:100mg";
		List<String> medications = new ArrayList<>();
		medications.add(medi);
		String all = "nillacilanUpdated";
		List<String> allergies = new ArrayList<>();
		allergies.add(all);
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/2000",medications,allergies );
		ObjectNode root = null;
		root= safetyAlertService.deletingRecordsFromMedicalRecords(record1);
		assertThat(root.findValues("FirstName").contains("John"));
	}

}
