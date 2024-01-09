package com.coronavirus.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.coronavirus.example.model.corona;
import com.coronavirus.example.service.CoronaVirusDataservice;



@org.springframework.stereotype.Controller
public class Controller {
	

	@Autowired
	CoronaVirusDataservice crnService;
	@GetMapping("/")
	public String home(Model model)
	{
		List<corona> allstates=crnService.getAllstates();
		int totalDeathsReported=allstates.stream().mapToInt(stat->stat.getLatestTotalDeaths()).sum();
		model.addAttribute("LocationStates",allstates);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		return "home";
	}

}
