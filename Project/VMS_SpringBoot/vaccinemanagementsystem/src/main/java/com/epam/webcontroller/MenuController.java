package com.epam.webcontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("managePeople")
	public String peopleMenu() {
		return "peoplemenu";
	}
	
	@RequestMapping("manageVaccine")
	public String vaccineMenu() {
		return "vaccinemenu";
	}
	
	@RequestMapping("returnToMenu")
	public String returnToMenu() {
		return "home";
	}
}
