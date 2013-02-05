package com.dimexer.spitter.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;
import com.dimexer.spitter.service.SpitterService;
import com.dimexer.spitter.service.SpittleService;

@Controller
public class SpitterController {

	private SpitterService spitterService;

	@Inject
	private SpittleService spittleService;
	
	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	public SpitterController() {
	}

	@RequestMapping(value = "/spitter/edit", method = RequestMethod.GET)
	public String loadSpitterProfile(Model model) {
		model.addAttribute("spitter", new Spitter());
		return "spitter/edit";
	}
	
	@RequestMapping(value = "/spitter/new", method = RequestMethod.GET)
	public String createFormObject(Model model) {
		model.addAttribute("spitter", new Spitter());
		return "spitter/edit";
	}

	@RequestMapping(value = "/spitter/new", method = RequestMethod.POST)
	public String saveSpitter(@Valid Spitter spitter,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return "/spitter/edit";
		}

		spitterService.addSpitter(spitter);
		return "redirect:/home";
	}

	@RequestMapping(value="/spitter/{username}", method=RequestMethod.GET)
	public String showSpitter(@PathVariable(value="username") String userName, Model model){
		Spitter sp = spitterService.findSpitterByUsername(userName);
		List<Spittle> spittles = spittleService.loadSpittlesForSpitter(sp);
		model.addAttribute("spitter", sp);
		model.addAttribute("spittles", spittles);
		return "/spitter/view";
	}
	
	@RequestMapping(value="/spitter/{username}", method=RequestMethod.POST, params="follow")
	public String followSpitter(@PathVariable(value="username") String username, Model model){
		Spitter currentSpitter = (Spitter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Spitter target = spitterService.findSpitterByUsername(username);
		spitterService.addFollower(target, currentSpitter);
		
		model.addAttribute("spitter", target);
		return "/spitter/view";
	}

	public SpitterService getSpittleService() {
		return spitterService;
	}

	public void setSpittleService(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

}
