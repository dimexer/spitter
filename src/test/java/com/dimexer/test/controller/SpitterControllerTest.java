package com.dimexer.test.controller;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.dimexer.spitter.controller.SpitterController;
import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.service.SpitterService;

public class SpitterControllerTest {
	
	@Test
	public void testGivingEmptySpitterForRegistration(){
		ExtendedModelMap testModel = new ExtendedModelMap();
		SpitterController controller = new SpitterController();
		assertEquals(controller.createFormObject(testModel), "spitter/edit");
		assertNotNull(testModel.get("spitter"));
	}
	
	@Test
	public void testSavingNewSpitter(){
		Spitter testSpitter = new Spitter(0, "test","test","test test", "test@test.com", null);
		SpitterController controller = new SpitterController();

		SpitterService spitterService = mock(SpitterService.class);
		when(spitterService.addSpitter(testSpitter)).thenReturn(1);
		
		controller.setSpittleService(spitterService);
		
		BindingResult bRes = mock(BindingResult.class);
		when(bRes.hasErrors()).thenReturn(true);
		when(bRes.getModel()).thenReturn(null);
		
		assertEquals(controller.saveSpitter(testSpitter, bRes, new ExtendedModelMap()), "/spitter/edit");

		when(bRes.hasErrors()).thenReturn(false);
		assertEquals(controller.saveSpitter(testSpitter, bRes, new ExtendedModelMap()), "redirect:/home");
	}
	
	@Test
	public void testShowingSpitterByUsername(){
		// TODO: implement
	}
}
