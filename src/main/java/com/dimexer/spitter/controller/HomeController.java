package com.dimexer.spitter.controller;

import java.security.Principal;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dimexer.spitter.model.Spitter;
import com.dimexer.spitter.model.Spittle;
import com.dimexer.spitter.service.SpittleService;

@Controller
public class HomeController {
	public static final int DEFAULT_SPITTERS_PER_PAGE = 25;

	private SpittleService spittleService;

	public HomeController() {

	}

	@Inject
	public HomeController(SpittleService spittleService) {
		this.spittleService = spittleService;
	}

	@RequestMapping({"/", "/home" })
	public String showHomePage(Map<String, Object> model, Principal p) {
		Spittle spittle = new Spittle();
		model.put("newSpittle", spittle);
		UserDetails activeUser = (UserDetails)((Authentication)p).getPrincipal();
		model.put("spittles", spittleService.getSpittleFromLastLogin((Spitter)activeUser, DEFAULT_SPITTERS_PER_PAGE));
		return "home";
	}

	public SpittleService getSpittleService() {
		return spittleService;
	}

	public void setSpittleService(SpittleService spittleService) {
		this.spittleService = spittleService;
	}
}
