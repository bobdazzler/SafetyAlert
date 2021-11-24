package com.safety;
import java.util.List;
import com.safety.model.Persons;
import com.safety.model.FireStations;
import com.safety.model.MedicalRecords;
public class SafetyNetData {
	public SafetyNetData() {
	}
	public SafetyNetData(List<Persons> persons, List<FireStations> firestations, List<MedicalRecords> medicalrecords) {
		this.persons = persons;
		this.firestations = firestations;
		this.medicalrecords = medicalrecords;
	}
	List<Persons> persons ;
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
