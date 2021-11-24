package com.safety.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.Test;
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

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safety.model.Persons;
import com.safety.service.SafetyAlertService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SafetyAlertService safetyAlertServiceMock;
	
	String personsMocked = "{\"firstName\": \"John\",\"lastName\": \"Boyd\",\"address\": \"1509 Culver St\",\"city\": \"Culver\",\"zip\": \"97451\",\"phone\": \"841-874-6512\",\"email\": \"jaboyd@email.com\"}";

	@Test
	public void testAddPerson() throws Exception {
		ObjectNode root = null;
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(root);
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
		ObjectNode root = null;
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(root);
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
		ObjectNode root = null;
		when(safetyAlertServiceMock.addingToListOfPersons((Mockito.any(Persons.class)))).
		thenReturn(root);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/person")
				.accept(MediaType.APPLICATION_JSON).content(personsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

}
