package com.safety.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.safety.dto.FireStationDTOHolder;
import com.safety.service.SafetyAlertService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@RunWith(SpringRunner.class)
class AlertControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SafetyAlertService safetyAlertService;


	@Test
	public void testWelcomeAlert() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().is2xxSuccessful());
	}
	
//	@Test
//	public void testListOfPeopleServicedByFireStation() throws Exception {
//		List<FireStationDTOHolder> fire = safetyAlertService.listOFPeopleServicedByFireStation("1");
//		mockMvc.perform(get("/firestations/stationNumber")
//				.param("station","1"))
//		.andExpect(MockMvcResultMatchers.content().json((fire.toString())))
//		.andExpect(status().isOk());
//	}

	@Test
	void testChildrenAtAddressServicedByFireStation() {
		fail("Not yet implemented");
	}

	@Test
	void testListOfPhoneNumbers() {
		fail("Not yet implemented");
	}

	@Test
	void testMedicals() {
		fail("Not yet implemented");
	}

	@Test
	void testHouseholdInStationJurisdiction() {
		fail("Not yet implemented");
	}

	@Test
	void testPersonsInformation() {
		fail("Not yet implemented");
	}

	@Test
	void testCommunityEmail() {
		fail("Not yet implemented");
	}

}
