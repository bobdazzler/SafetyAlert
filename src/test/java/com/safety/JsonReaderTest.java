package com.safety;


import static org.junit.jupiter.api.Assertions.*;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class JsonReaderTest {
	Logger logger = LoggerFactory.getLogger(JsonReaderTest.class);
	
	@InjectMocks
	JsonReader jsonReader;
	@Mock
	SafetyNetData dataMocked;
		
	@Test
	public void jsonDataReaderTest() {
		SafetyNetData dataFromJsonUnderTest = jsonReader.jsonDataReader();
		assertNotNull(dataFromJsonUnderTest);
		assertNotNull(jsonReader.listOfFireStations());
		assertNotNull(jsonReader.listOfMedicalRecords());
		assertNotNull(jsonReader.listOfPersons());

}
}