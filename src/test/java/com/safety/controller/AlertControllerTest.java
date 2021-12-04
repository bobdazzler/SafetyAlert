package com.safety.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.safety.dto.ChildAlertDTO;
import com.safety.dto.ChildAndAdultDTO;
import com.safety.dto.CommunityEmailDTO;
import com.safety.dto.FireAlertDTO;
import com.safety.dto.FireStationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.safety.dto.FireStationDTOHolder;
import com.safety.dto.FloodStationDTO;
import com.safety.dto.FloodStationHolderDTO;
import com.safety.dto.FloodStationHolderDTOAndAddress;
import com.safety.dto.PersonInfoDTO;
import com.safety.dto.PhoneAlertDTO;
import com.safety.service.SafetyAlertService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AlertController.class)
public class AlertControllerTest {
	

	public AlertControllerTest() {
	}

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SafetyAlertService safetyAlertServiceMock;


	@Test
	public void testWelcomeAlert() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testListOfPeopleServicedByFireStation() throws Exception {
		//arrange
		FireStationDTO fireStationDTO = new FireStationDTO("john","Oghoro","asaba","08062078262","47");
		List<FireStationDTO> itemsMocked = new ArrayList<>();
		itemsMocked.add(fireStationDTO);
		FireStationDTOHolder fireStationMocked = new FireStationDTOHolder(itemsMocked,"0","1");
		List<FireStationDTOHolder>  mockedstation = new ArrayList<>();
		mockedstation.add(fireStationMocked);
		//act
		when(safetyAlertServiceMock.listOFPeopleServicedByFireStation
				(Mockito.anyString())).thenReturn(mockedstation);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/firestation").param("stationNumber","3").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"fireStationDTOSHolder\":[{\"firstName\":\"john\",\"lastName\":\"Oghoro\",\"address\":\"asaba\",\"phoneNumber\":\"08062078262\",\"age\":\"47\"}],\"ageSummaryForChildren\":\"0\",\"ageSummaryForAdult\":\"1\"}]";
		//assert
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}

	@Test
	public void testChildrenAtAddressServicedByFireStation() throws Exception {
		//arrange
		ChildAlertDTO childAlert = new ChildAlertDTO("Oghoro", "Kobi", 2);
		List<ChildAlertDTO> childAlertDTO = new ArrayList<>();
		childAlertDTO.add(childAlert);
		String adult = "Oghoro" + " Tega";
		List<String> adultmocked = new ArrayList<>();
		adultmocked.add(adult);
		ChildAndAdultDTO childAndAdult = new ChildAndAdultDTO(childAlertDTO , adultmocked);
		List<ChildAndAdultDTO> childAndAdultMocked = new ArrayList<>();
		childAndAdultMocked.add(childAndAdult);
		//act
		when(safetyAlertServiceMock.childAlertAPI((Mockito.anyString()))).
		thenReturn(childAndAdultMocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/childAlert").
				param("address","1509 Culver St").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"childrenAtAddress\":[{\"firstName\":\"Oghoro\",\"lastName\":\"Kobi\",\"age\":2}],\"adultAtAddress\":[\"Oghoro Tega\"]}]";
		//assert
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void testListOfPhoneNumbers() throws Exception {
		//arrange
		PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO("08126351314");
		List<PhoneAlertDTO> phoneAlertDTOMocked = new ArrayList<>();
		phoneAlertDTOMocked.add(phoneAlertDTO);
		//act
		when(safetyAlertServiceMock.getPhoneNumberByAddress((Mockito.anyString()))).
		thenReturn(phoneAlertDTOMocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/phoneAlert").
				param("firestation","3").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"phoneNumber\":\"08126351314\"}]";
		//assert
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void testMedicals() throws Exception {
		//arrange
		List<String> medications = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("nillacilan");
		FireAlertDTO fireAlertDTO = new FireAlertDTO("1","Oghoro","Bob","34",medications,allergies);
		List<FireAlertDTO> fireAlertDTOMocked = new ArrayList<>();
		fireAlertDTOMocked.add(fireAlertDTO);
		//act
		when(safetyAlertServiceMock.listOfPeopleServicedByStationNumberAfterGettingAddress((Mockito.anyString()))).
		thenReturn(fireAlertDTOMocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fire").
				param("address","1509 Culver St").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"stationNumber\":\"1\",\"firstName\":\"Oghoro\",\"lastName\":\"Bob\",\"age\":\"34\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}]";
		//assert
				JSONAssert.assertEquals(expected, result.getResponse()
						.getContentAsString(), false);
	}

	@Test
	public void testHouseholdInStationJurisdiction() throws Exception {
		//arrange
		List<String> medications = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("nillacilan");
		FloodStationDTO floodStationDTO = new FloodStationDTO("Oghoro","Leo","08129549883","34",medications,allergies);
		List<FloodStationDTO> floodStationDTOHolder = new ArrayList<>();
		floodStationDTOHolder.add(floodStationDTO);
		FloodStationHolderDTO floodStationHolderDTO = new FloodStationHolderDTO("asaba",floodStationDTOHolder);
		List<FloodStationHolderDTO> floodStationHolderDTOlist = new ArrayList<>();
		floodStationHolderDTOlist.add(floodStationHolderDTO);
		FloodStationHolderDTOAndAddress floodStationAndAdress = new FloodStationHolderDTOAndAddress(floodStationHolderDTOlist);
		List<FloodStationHolderDTOAndAddress> holdermocked = new ArrayList<>();
		holdermocked.add(floodStationAndAdress);
		//act
		when(safetyAlertServiceMock.houseHoldInFireStationJurisdiction((Mockito.anyList()))).
		thenReturn(holdermocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flood/stations").
				param("stations","2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getRequest().getContentAsString());
		String expected = "[{\"peopleAffected\":[{\"address\":\"asaba\",\"peopleAffected\":[{\"firstName\":\"Oghoro\",\"lastName\":\"Leo\",\"phoneNumber\":\"08129549883\",\"age\":\"34\",\"allergies\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"medications\":[\"nillacilan\"]}]}]}]";
		//assert
				JSONAssert.assertEquals(expected, result.getResponse()
						.getContentAsString(), false);
	}

	@Test
	public void testPersonsInformation() throws Exception {
		//arrange
		List<String> medications = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("nillacilan");
		PersonInfoDTO personInfoDTO = new PersonInfoDTO("Oghoro","Bob","24","asaba","oghoroBob@gmail.com",medications, allergies);
		List<PersonInfoDTO> personsInfoDTOMocked= new ArrayList<>();
		personsInfoDTOMocked.add(personInfoDTO);
		//act
		when(safetyAlertServiceMock.personInfo((Mockito.anyString()),Mockito.anyString())).
		thenReturn(personsInfoDTOMocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/personInfo").
				param("firstName","Oghoro").accept(MediaType.APPLICATION_JSON).
				param("lastName","Bob").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getRequest().getContentAsString());
		String expected = "[{\"firstName\":\"Oghoro\",\"lastName\":\"Bob\",\"age\":\"24\",\"address\":\"asaba\",\"email\":\"oghoroBob@gmail.com\",\"medication\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]}]";
		//assert
				JSONAssert.assertEquals(expected, result.getResponse()
						.getContentAsString(), false);
	}

	@Test
	public void testCommunityEmail() throws Exception {
		CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO("OghoroBob@gmail.com");
		List<CommunityEmailDTO> communityEmailDTOMocked= new ArrayList<>();
		communityEmailDTOMocked.add(communityEmailDTO);
		when(safetyAlertServiceMock.gettingListOfEmailFromCity((Mockito.anyString()))).
		thenReturn(communityEmailDTOMocked);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/communityEmail").
				param("city","asaba").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getRequest().getContentAsString());
		String expected = "[{\"email\":\"OghoroBob@gmail.com\"}]";
		//assert
				JSONAssert.assertEquals(expected, result.getResponse()
						.getContentAsString(), false);
	}

}
