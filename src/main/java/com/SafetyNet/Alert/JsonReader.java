package com.SafetyNet.Alert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;
import com.google.gson.Gson;

public class JsonReader {
List <FireStations> firestations = new ArrayList<>();
List <Persons> persons = new ArrayList<>();
List <MedicalRecords> medicalrecords = new ArrayList<>();
	

	
	 
	public List<FireStations> getFirestations() {
	return firestations;
}

public void setFirestations(List<FireStations> firestations) {
	this.firestations = firestations;
}

public List<Persons> getPersons() {
	return persons;
}

public void setPersons(List<Persons> persons) {
	this.persons = persons;
}

public List<MedicalRecords> getMedicalrecords() {
	return medicalrecords;
}

public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
	this.medicalrecords = medicalrecords;
}

public SafetyNetData jsonDataReader() {
	SafetyNetData safetyNetData = null;
	try 
	{
		
		String filePath ="src/main/resources/data.json";
		BufferedReader reader = new BufferedReader(new InputStreamReader
				(new FileInputStream(filePath)));
			Gson gson = new Gson();
			 safetyNetData = gson.fromJson(reader, SafetyNetData.class);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
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

