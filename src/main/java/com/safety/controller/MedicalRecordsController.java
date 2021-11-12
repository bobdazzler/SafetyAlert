package com.safety.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.safety.model.MedicalRecords;
import com.safety.service.SafetyAlertService;
@RestController
public class MedicalRecordsController {
	SafetyAlertService safetyAlertService;
	@Autowired
	public MedicalRecordsController(SafetyAlertService safetyAlertService) {
		this.safetyAlertService = safetyAlertService;
	}
	@PostMapping("/medicalRecords")
	public void addMedicalRecords(@RequestBody MedicalRecords records) {
		safetyAlertService.addingToMedicalRecords(records);
	}
	@PutMapping("/medicalRecords")
	public void updateMedicalRecords(@RequestBody MedicalRecords records) {
		safetyAlertService.updatingMedicalRecords(records);
	}
	@DeleteMapping("/medicalRecords")
	public void deletingRecordFromMedicalRecords(@RequestBody MedicalRecords records) {
		safetyAlertService.deletingRecordsFromMedicalRecords(records);
	}
}
