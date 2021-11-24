package com.safety;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.safety.model.FireStations;
import com.safety.model.MedicalRecords;
import com.safety.model.Persons;

@RunWith(SpringRunner.class)
public class JsonReaderTest {
	Logger logger = LoggerFactory.getLogger(JsonReaderTest.class);
	
	@Mock
	JsonReader jsonReader;
	@Mock
	SafetyNetData dataMocked;
		
	@Test
	public void jsonDataReaderTest() {
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
			SafetyNetData safe = new SafetyNetData(mockedPersons,fireStationMocked,recordMocked);
			 when(jsonReader.jsonDataReader()).thenReturn(safe);
			 assertNotNull(jsonReader.jsonDataReader().getFireStations());
			 assertNotNull(jsonReader.jsonDataReader().getMedicalRecords());
			 assertNotNull(jsonReader.jsonDataReader().getPersons());
		     assertThat(jsonReader.jsonDataReader().equals(safe));
		     SafetyNetData safe1 = jsonReader.jsonDataReader();
		     assertNotNull(safe1);
		     assertNotNull(jsonReader.listOfFireStations());
		     assertNotNull(jsonReader.listOfPersons());
		     assertNotNull(jsonReader.listOfMedicalRecords());
	}
	
	@Test
	public void testingForJsonReaderDataObject() {
		SafetyNetData safetyNetData = null;
		try 
		{
			String filePath ="src/main/resources/data.json";
			BufferedReader reader = new BufferedReader(new InputStreamReader
					(new FileInputStream(filePath)));
			Gson gson = new Gson();
			safetyNetData = gson.fromJson(reader, SafetyNetData.class);
		}catch(FileNotFoundException fE)
		{
			logger.error("an error occured so no data was read "+fE);
		}
		assertNotNull(safetyNetData);
	}

}
