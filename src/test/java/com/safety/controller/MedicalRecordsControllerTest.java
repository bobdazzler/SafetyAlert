package com.safety.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import com.safety.model.MedicalRecords;
import com.safety.service.SafetyAlertService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = MedicalRecordsController.class)
public class MedicalRecordsControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SafetyAlertService safetyAlertServiceMock;
	String medicalRecordsMocked =" {\"firstName\": \"John\",\"lastName\": \"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\": [\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\": [\"nillacilan\"]}";
	@Test
public	void testAddMedicalRecords() throws Exception {
		ObjectNode root = null;
	//	MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",Arrays.asList("aznol:350mg","hydrapermazol:100mg"),Arrays.asList("nillacilan"));
		when(safetyAlertServiceMock.addingToMedicalRecords((Mockito.any(MedicalRecords.class)))).
		thenReturn(root);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/medicalRecords")
				.accept(MediaType.APPLICATION_JSON).content(medicalRecordsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

	@Test
public	void testUpdateMedicalRecords() throws Exception {
		ObjectNode root = null;
		//MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",Arrays.asList("aznol:350mg","hydrapermazol:100mg"),Arrays.asList("nillacilan"));
		when(safetyAlertServiceMock.addingToMedicalRecords((Mockito.any(MedicalRecords.class)))).
		thenReturn(root);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/medicalRecords")
				.accept(MediaType.APPLICATION_JSON).content(medicalRecordsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

	@Test
public	void testDeletingRecordFromMedicalRecords() throws Exception {
		ObjectNode root = null;
		//MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",Arrays.asList("aznol:350mg","hydrapermazol:100mg"),Arrays.asList("nillacilan"));
		when(safetyAlertServiceMock.addingToMedicalRecords((Mockito.any(MedicalRecords.class)))).
		thenReturn(root);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/medicalRecords")
				.accept(MediaType.APPLICATION_JSON).content(medicalRecordsMocked)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
	}

}
