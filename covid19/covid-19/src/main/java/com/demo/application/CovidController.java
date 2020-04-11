/**
 * 
 */
package com.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author PooraniArya
 *
 */
@Controller
public class CovidController {
	@Autowired
	CovidDataServices covidService;
	
	@GetMapping("/")
	public String getDetails(Model model) {
		model.addAttribute("covidStats", covidService.getLocationList());
		model.addAttribute("totalReportedCases", covidService.getLocationList().stream().mapToInt(count -> count.getLatestCount()).sum());
		return "home";
	}
}
