package com.safety.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import com.safety.JsonReader;
import com.safety.SafetyNetData;
import com.safety.dto.FireStationDTOHolder;
import com.safety.model.MedicalRecords;
import org.springframework.test.context.junit4.SpringRunner;
import com.safety.model.Persons;
@RunWith(SpringRunner.class)
class SafetyAlertServiceTest {
	
	@InjectMocks
	SafetyAlertService safetyAlertService;
	@Mock
	JsonReader jsonReader;
	@Mock
	SafetyNetData safetyMock;
	@Test
	public void testListOFPeopleServicedByFireStation() {
		//arrange
		Persons persons1 = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		Persons persons2 = new Persons("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"); 
		List<Persons> mockedPersons =new ArrayList<>();
		mockedPersons.add(persons2);
		mockedPersons.add(persons1);
		List<String> medications = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("nillacilan");
		MedicalRecords record1 = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies );
		MedicalRecords record2 = new MedicalRecords();
		List<MedicalRecords> recordMocked = new ArrayList<>();
		recordMocked.add(record2);
		recordMocked.add(record1);
		when(jsonReader.listOfPersons()).thenReturn(mockedPersons);
		when(jsonReader.listOfMedicalRecords()).thenReturn(recordMocked);
		//act
		FireStationDTOHolder holder = new FireStationDTOHolder();
		List<FireStationDTOHolder> methodUnderTest = safetyAlertService.listOFPeopleServicedByFireStation("1");
		//assert
		assertTrue(methodUnderTest.contains(holder.getFireStationDTOSHolder()));
	}

	@Test
	void testGetAddressByStationNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testNamesOfPersonsLeavingInAnAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testChildAlertAPI() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPhoneNumberByAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testFireStationNumberFromAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testListOfPeopleServicedByStationNumberAfterGettingAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testHouseHoldListOfPeople() {
		fail("Not yet implemented");
	}

	@Test
	void testHouseHoldInFireStationJurisdiction() {
		fail("Not yet implemented");
	}

	@Test
	void testGettingEmailsOfIndividuals() {
		fail("Not yet implemented");
	}

	@Test
	void testGettingAddressFromNames() {
		fail("Not yet implemented");
	}

	@Test
	void testPersonInfo() {
		fail("Not yet implemented");
	}

	@Test
	void testGettingListOfEmailFromCity() {
		fail("Not yet implemented");
	}

	@Test
	void testAddingToListOfPersons() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdatingListOfPersons() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletingFromListOfPersons() {
		fail("Not yet implemented");
	}

	@Test
	void testAddingToListOfFireStations() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdatingListOfFireStation() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletingFromListOFFireStation() {
		fail("Not yet implemented");
	}

	@Test
	void testAddingToMedicalRecords() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdatingMedicalRecords() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletingRecordsFromMedicalRecords() {
		fail("Not yet implemented");
	}

}
