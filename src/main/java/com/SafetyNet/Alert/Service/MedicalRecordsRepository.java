//package com.SafetyNet.Alert;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
//
//public class MedicalRecordsRepository {
////	Logger logger = LoggerFactory.getLogger(MedicalRecordsRepository.class);
//	PersonRepository people = new PersonRepository();
//	JsonReader jsonReader = new JsonReader();
//	FireStationsRepository fireStationsRepository = new FireStationsRepository();
//
//	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//	Date date = new Date();
//	String now = dateFormat.format(date);
//
//
//


//
//	public List<String> phoneNumberOfPeople(String address) {
//		List <String> phoneNumber = new ArrayList<>();
//      try{
//		for(Persons personsPhone:jsonReader.listOfPersons()) {
//			for(int i = 0; i<people.getFirstNamesOfPersonsLeavingInAnAddress(address).size();i++) {
//				if(people.getFirstNamesOfPersonsLeavingInAnAddress(address).get(i).equals(personsPhone.getFirstName())) {
//					phoneNumber.add(personsPhone.getPhone());
//				}
//			}
//
//		}
//		if(!jsonReader.listOfPersons().isEmpty()){}
//	  }catch(Exception e) {
//	//	  logger.error("an error occurred"+e);
//	  }
//
//		return phoneNumber;
//
//	}
//
//	public String  ageSummaryForNumberOfChildren(String address) {
//
//			int count = 0;
//			String ageSummaryNumber = null;
//			try{
//			for (MedicalRecords medicalRecords : jsonReader.listOfMedicalRecords()) {
//				for (int i = 0; i < people.getFirstNamesOfPersonsLeavingInAnAddress(address).size(); i++) {
//
//						if (((medicalRecords.getFirstName().equals(people.getFirstNamesOfPersonsLeavingInAnAddress(address).
//								get(i)))) && (((dateFormat.parse(now).getYear())) -
//								dateFormat.parse(medicalRecords.getBirthdate()).getYear()) < 18) {
//							count++;
//						}
//						ageSummaryNumber = Integer.toString(count);
//				}
//
//
//			}
//		} catch (Exception e) {
//	//		logger.error("an error was thrown" + e);
//		}
//		return "Age summary for children: " + ageSummaryNumber;
//	}
//	public String ageSummaryForNumberOfAdult(String address) {
//		int count = 0;
//		String ageSummaryNumber = null;
//		try{
//		for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
//			for(int i =0; i<people.getFirstNamesOfPersonsLeavingInAnAddress(address).size();i++) {
//
//					if(((medicalRecords.getFirstName().equals(people.
//							getFirstNamesOfPersonsLeavingInAnAddress(address).
//							get(i))))&&(((dateFormat.parse(now).getYear())) -
//									dateFormat.parse(medicalRecords.getBirthdate()).getYear()) >18)  {
//						count++;
//						ageSummaryNumber=Integer.toString(count);
//
//					}
//
//
//
//			}
//
//		}
//		}catch (Exception e){
//		//	logger.error("an error was thrown" + e);
//		}
//		return"age summary "+ ageSummaryNumber;
//	}
//
//	public String ageDifferneceOfPeople(String address) {
//		String ageDifferences = null;
//		try{
//		for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
//				for(int i =0; i<people.getFirstNamesOfPersonsLeavingInAnAddress(address).size();i++) {
//					if((medicalRecords.getFirstName().equals(people.
//							getFirstNamesOfPersonsLeavingInAnAddress(address).
//							get(i)))){
//						int age =	(dateFormat.parse(now).getYear())-dateFormat.parse(medicalRecords.getBirthdate()).getYear();
//						ageDifferences=Integer.toString(age);
//
//					}
//				}
//
//		}
//		}catch (Exception e){
//		//	logger.error("an error was thrown" + e);
//		}
//		return ageDifferences;
//
//	}
//	public List<String> houseHoldListOfPeople(String address){
//		List<String>listOfPeople = new ArrayList<>();
//		try {
//			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
//				for (String names : people.getFirstNamesOfPersonsLeavingInAnAddress(address)) {
//
//					if (names.contains(medicalRecords.getFirstName())) {
//						int ageDifferrences = ((dateFormat.parse(now).getYear()) -
//								dateFormat.parse(medicalRecords.getBirthdate()).getYear());
//
//						String ageDifferencesString = Integer.toString(ageDifferrences);
//						listOfPeople.add(medicalRecords.getFirstName());
//						listOfPeople.add(medicalRecords.getLastName());
//						listOfPeople.add(ageDifferencesString);
//						listOfPeople.addAll(medicalRecords.getAllergies());
//						listOfPeople.addAll(medicalRecords.getMedications());
//
//					}
//
//
//				}
//			}
//
//		if(!people.getFirstNamesOfPersonsLeavingInAnAddress(address).isEmpty()) {
//
//		}
//		}catch (Exception e){
//		//	logger.error("an error was thrown" + e);
//		}
//		return listOfPeople;
//
//	}
//	public List<String> personInfo(String firstName, String lastName){
//		List<String> personsInformation = new ArrayList<>();
//		try {
//			for (MedicalRecords medicalRecords:jsonReader.listOfMedicalRecords()) {
//				if(medicalRecords.getFirstName().contains(firstName)&&medicalRecords.getLastName().contains(lastName))
//				{
//					int ageDifferrences = ((dateFormat.parse(now).getYear()) -
//							dateFormat.parse(medicalRecords.getBirthdate()).getYear());
//					String ageDifferencesString = Integer.toString(ageDifferrences);
//					personsInformation.add(medicalRecords.getFirstName());
//					personsInformation.add(medicalRecords.getLastName());
//					personsInformation.add(ageDifferencesString);
//					personsInformation.add(gettingAddressFromNames(firstName,lastName));
//					personsInformation.add(gettingEmailsOfIndividuals(firstName, lastName));
//					personsInformation.addAll(medicalRecords.getMedications());
//
//
//
//				}
//			}
//		}catch(Exception e) {
//		//	logger.error("an error occurred" + e);;
//		}
//		return personsInformation;
//	}
//	public String gettingAddressFromNames(String firstName , String lastName) {
//		String addressFromNamesOfPeople = null;
//		try{
//		for(Persons persons : jsonReader.listOfPersons()) {
//			if((persons.getFirstName().contains(firstName))&&persons.getLastName().contains(lastName)) {
//				addressFromNamesOfPeople = persons.getAddress();
//			}
//		}}catch (Exception e){
//		//	logger.error("an error was thrown" + e);
//		}
//		return addressFromNamesOfPeople;
//	}
//	public String gettingEmailsOfIndividuals(String firstName, String lastName) {
//		String emailFromNamesOfPeople = null;
//		try {
//			for (Persons persons : jsonReader.listOfPersons()) {
//				if ((persons.getFirstName().contains(firstName)) && persons.getLastName().contains(lastName)) {
//					emailFromNamesOfPeople = persons.getEmail();
//				}
//			}
//		}catch (Exception e){
//		//logger.error("an error was thrown" + e);
//	}
//		return emailFromNamesOfPeople;
//	}
//}
//
//
//
