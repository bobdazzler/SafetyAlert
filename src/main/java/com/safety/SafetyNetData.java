package com.safety;
import java.util.List;
import com.safety.model.Persons;
import com.safety.model.FireStations;
import com.safety.model.MedicalRecords;
public class SafetyNetData {
	List<Persons> persons ;
	List<FireStations> firestations;
	List<MedicalRecords> medicalrecords;
	public List<Persons> getPersons() {
		return persons;
	}
	
	public List<FireStations> getFireStations() {
		return firestations;
	}

	public List<MedicalRecords> getMedicalRecords() {
		return medicalrecords;
	}
	
}
