package com.safety.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safety.model.FireStations;
import com.safety.service.SafetyAlertService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = FireStationController.class)
class FireStationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SafetyAlertService safetyAlertServiceMock;
	String fire ="{\"address\":\"asaba\",\"station\":\"5\"}";
	@Test
	public void testAddFireStation() throws Exception {

		FireStations fireStation = new FireStations("asaba","5");
		when(safetyAlertServiceMock.addingToListOfFireStations((Mockito.any(FireStations.class)))).
		thenReturn(fireStation);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/fireStation")
				.accept(MediaType.APPLICATION_JSON).content(fire)
				.contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();


		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());


	}

	@Test
	void testUpdateFireStation() throws Exception {
		FireStations fireStation = new FireStations("asaba","5");
		when(safetyAlertServiceMock.updatingListOfFireStation((Mockito.any(FireStations.class)),Mockito.anyString())).
		thenReturn(fireStation);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/fireStation")
				.param("address", "asaba")
				.accept(MediaType.APPLICATION_JSON).content(fire)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());


	}

	@Test
	void testDeleteFireStation() throws Exception {
		FireStations fireStation = new FireStations("asaba","5");
		when(safetyAlertServiceMock.updatingListOfFireStation((Mockito.any(FireStations.class)),Mockito.anyString())).
		thenReturn(fireStation);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/fireStation")
				.accept(MediaType.APPLICATION_JSON).content(fire)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());


	}

}
