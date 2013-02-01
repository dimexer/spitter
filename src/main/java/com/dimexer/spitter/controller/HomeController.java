package com.dimexer.spitter.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dimexer.spitter.model.Spitter;
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
	public String showHomePage(Map<String, Object> model) {
		model.put("spittles",
				spittleService.getRecentSpittles(DEFAULT_SPITTERS_PER_PAGE));
		return "home";
	}

	public SpittleService getSpittleService() {
		return spittleService;
	}

	public void setSpittleService(SpittleService spittleService) {
		this.spittleService = spittleService;
	}
}
