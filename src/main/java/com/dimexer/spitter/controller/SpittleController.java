package com.dimexer.spitter.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;
import com.dimexer.spitter.service.SpittleService;

@Controller
public class SpittleController {

	private SpittleService spittleService;

	@Inject
	public SpittleController(SpittleService spittleService){
		this.spittleService=spittleService;
	}
	
	@RequestMapping(value="/spittle/publish", method=RequestMethod.POST)
	public String publishSpittle(@Valid Spittle spittle, BindingResult result){
		if(result.hasErrors())
			return null;

		Spitter spitter = (Spitter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		spittle.setSpitter(spitter);
		spittleService.insertSpittle(spittle);
		
		return "redirect:/home";
	}
	
	public SpittleService getSpittleService() {
		return spittleService;
	}

	public void setSpittleService(SpittleService spittleService) {
		this.spittleService = spittleService;
	}
	
	
}
