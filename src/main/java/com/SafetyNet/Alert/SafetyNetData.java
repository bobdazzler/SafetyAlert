package com.SafetyNet.Alert;

import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;

import java.util.List;

public class SafetyNetData {
	List<Persons> persons;
	List<FireStations> firestations;
	List<MedicalRecords> medicalrecords;
	public List<Persons> getPersons() {
		return persons;
	}
	public void setPersons(List<Persons> persons) {
		this.persons = persons;
	}
	public List<FireStations> getFireStations() {
		return firestations;
	}
	public void setFireStations(List<FireStations> firestations) {
		this.firestations = firestations;
	}
	public List<MedicalRecords> getMedicalRecords() {
		return medicalrecords;
	}
	public void setMedicalRecords(List<MedicalRecords> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}
