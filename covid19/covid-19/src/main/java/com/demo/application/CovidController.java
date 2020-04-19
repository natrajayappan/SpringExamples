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
	
	@GetMapping("/home")
	public String getDetails(Model model) {
		model.addAttribute("covidStats", covidService.getLocationList());
		model.addAttribute("totalReportedCases", covidService.getLocationList().stream().mapToInt(count -> count.getLatestCount()).sum());
		model.addAttribute("difference", (covidService.getLocationList().stream().mapToInt(count -> count.getLatestCount()).sum() - 
				covidService.getLocationList().stream().mapToInt(count -> count.getYesterdayCount()).sum()));
		
		//Iterating only USA cases
		covidService.getLocationList().forEach(locationDetail -> {
			if("US".equals(locationDetail.getCountry())) {
				int usNewCases = locationDetail.getLatestCount() - locationDetail.getYesterdayCount();
				model.addAttribute("usNewCases", usNewCases);
			}
		});
		
		
		return "home";
	}
}
