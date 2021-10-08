//package com.SafetyNet.Alert.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.SafetyNet.Alert.JsonReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.SafetyNet.Alert.DTO.ChildAlertDTO;
//import com.SafetyNet.Alert.Model.Persons;
//
//public class PersonRepository {
//	Logger logger = LoggerFactory.getLogger(PersonRepository.class);
//	JsonReader jsonReader = new JsonReader();
//
//
//
//
//
//	}
//	public List<String> gettingListOfEmailFromCity(String city){
//		List <String> emails = new ArrayList<>();
//		try {
//			for (Persons persons : jsonReader.listOfPersons()) {
//				if (persons.getCity().contains(city)) {
//					emails.add(persons.getEmail());
//				}
//			}
//		}catch(Exception e){
//			logger.error("an error was thrown" +e);
//		}
//		return emails;
//
//	}
//}
