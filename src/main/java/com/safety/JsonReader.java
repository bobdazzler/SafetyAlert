package com.safety;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.safety.model.FireStations;
import com.safety.model.Persons;
import com.safety.model.MedicalRecords;
import com.google.gson.Gson;
@Service
public class JsonReader {
	Logger logger =  LoggerFactory.getLogger(JsonReader.class);
	List <FireStations> firestations = new ArrayList<>();
	List <Persons> persons = new ArrayList<>();
	List <MedicalRecords> medicalrecords = new ArrayList<>();	 
	@PostConstruct
	public SafetyNetData jsonDataReader() {
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
		return safetyNetData;
	}
	public List<FireStations> listOfFireStations(){
		firestations= jsonDataReader().getFireStations();
		return firestations;
	}
	public List<Persons> listOfPersons(){
		persons = jsonDataReader().getPersons();
		return persons;
	}
	public List<MedicalRecords> listOfMedicalRecords(){
		medicalrecords = jsonDataReader().getMedicalRecords();
		return medicalrecords;
	}
}
