package com.safety.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import com.safety.model.Persons;
import com.safety.service.SafetyAlertService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
class PersonControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SafetyAlertService safetyAlertServiceMock;
	
	String personsMocked = "{\"firstName\": \"John\",\"lastName\": \"Boyd\",\"address\": \"1509 Culver St\",\"city\": \"Culver\",\"zip\": \"97451\",\"phone\": \"841-874-6512\",\"email\": \"jaboyd@email.com\"}";

	@Test
	public void testAddPerson() throws Exception {
		Persons persons = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(persons);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/person")
				.accept(MediaType.APPLICATION_JSON).content(personsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

	@Test
	public void testUpdatePerson() throws Exception {
		Persons persons = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(persons);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/person")
				.accept(MediaType.APPLICATION_JSON).content(personsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

	@Test
	public void testDeletePerson() throws Exception {
		Persons persons = new Persons("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(persons);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/person")
				.accept(MediaType.APPLICATION_JSON).content(personsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

}
