package com.dimexer.spitter.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@RequestMapping(value="/spittle/ajaxpublish", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void publishSpittleAjax(String spittle){
		Spittle newSpittle = new Spittle();
		newSpittle.setText(spittle);
		newSpittle.setTime(new Date());
		Spitter author = (Spitter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		newSpittle.setSpitter(author);
		spittleService.insertSpittle(newSpittle);
	}
	
	public SpittleService getSpittleService() {
		return spittleService;
	}

	public void setSpittleService(SpittleService spittleService) {
		this.spittleService = spittleService;
	}
	
	
}
