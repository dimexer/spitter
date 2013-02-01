package com.dimexer.spitter.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.service.SpitterService;

@Controller
public class SpitterController {

	private SpitterService spitterService;

	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	public SpitterController() {
	}

	@RequestMapping(value = "/spitter/edit", method = RequestMethod.GET, params = "new")
	public String createFormObject(Model model) {
		model.addAttribute("spitter", new Spitter("", "", "", ""));
		return "spitter/edit";
	}

	@RequestMapping(value = "/spitter/edit", params = "new", method = RequestMethod.POST)
	public String saveSpitter(@Valid Spitter spitter,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/spitter/edit";
		}

		spitterService.addSpitter(spitter);
		return "redirect:/home";
	}

	/*
	 * @RequestMapping(value="/spitter/{username}", method=RequestMethod.GET)
	 * public String showSpitterProfile(@PathVariable String username){
	 * 
	 * return "spitter/view"; }
	 */

	public SpitterService getSpittleService() {
		return spitterService;
	}

	public void setSpittleService(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

}
