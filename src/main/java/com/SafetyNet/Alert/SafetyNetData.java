package com.SafetyNet.Alert;

import com.SafetyNet.Alert.Model.FireStations;
import com.SafetyNet.Alert.Model.MedicalRecords;
import com.SafetyNet.Alert.Model.Persons;

import java.util.ArrayList;
import java.util.List;

public class SafetyNetData {
	List<Persons> persons = new ArrayList<>();
	List<FireStations> firestations;
	List<MedicalRecords> medicalrecords;
	public List<Persons> getPersons() {
		return persons;
	}
	public void setPersons(Persons persons) {
		this.persons.add(persons);
	}
	public List<FireStations> getFireStations() {
		return firestations;
	}
	public void setFireStations(FireStations firestations) {
		this.firestations.add(firestations);
	}
	public List<MedicalRecords> getMedicalRecords() {
		return medicalrecords;
	}
	public void setMedicalRecords(MedicalRecords medicalrecords) {
		this.medicalrecords.add(medicalrecords);
	}
}
