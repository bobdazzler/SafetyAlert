package com.safety.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.safety.model.Persons;
import com.safety.service.SafetyAlertService;
@RestController
public class PersonController {
	SafetyAlertService safetyAlertService;
	@Autowired
	public PersonController(SafetyAlertService safetyAlertService) {
		this.safetyAlertService = safetyAlertService;
	}
	@PostMapping("/person")
	public void addPerson(@RequestBody Persons person) {
		safetyAlertService.addingToListOfPersons(person);
	}
	@PutMapping("/person")
	public void updatePerson(@RequestBody Persons person){
		safetyAlertService.updatingListOfPersons(person);
	}
	@DeleteMapping("/person")
	public void deletePerson(@RequestBody Persons person ) {
		safetyAlertService.deletingFromListOfPersons(person);
	}

}
