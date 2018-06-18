package com.timcoville.relationships.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.timcoville.relationships.models.License;
import com.timcoville.relationships.models.Person;
import com.timcoville.relationships.service.LicenseService;

@Controller
public class LicenseController {
	private final LicenseService licenseService;
	public LicenseController(LicenseService licenseService) {
		this.licenseService = licenseService;
	}
	
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("Person") Person person) {
		return "/newPerson.jsp";
	}
	
	@RequestMapping(value="/persons", method=RequestMethod.POST)
	public String createPerson(@Valid @ModelAttribute("Person")Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "/newPerson.jsp";
		} else {
			licenseService.createPerson(person);
			return "redirect:/licenses/new";
		}
	}
	
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("License") License license, Model model) {
		List<Person> people = licenseService.allPeople();
		model.addAttribute("people", people);
		return "/newLicense.jsp";
	}
	
	@RequestMapping(value="/licenses", method=RequestMethod.POST)
	public String createLicense(@Valid @ModelAttribute("License")License license, @RequestParam("expirationDate")String expirationDate, BindingResult result) {
		List<License> licenses = licenseService.allLicenses();
		
		if (licenses.size() == 0) {
			String licenseNumber = "000001";
			license.setNumber(licenseNumber);
		} else {
			License mostRecent = licenses.get(0);
			String lastNumber = mostRecent.getNumber();
			int number = Integer.parseInt(lastNumber); 
			number++;
			String numberStr = String.valueOf(number);
			String prePend = "";
			for (int i = 0; i < 6-numberStr.length(); i++) {
				prePend += "0";
			}
			String licenseNumber = prePend.concat(numberStr);
			license.setNumber(licenseNumber);
		}
		
		DateFormat expDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			Date tempDate = expDate.parse(expirationDate);
			license.setExpiration_date(tempDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		if (result.hasErrors()) {
			System.out.println("found Error");
			System.out.println("result" + result);
			return "/newLicense.jsp";
		} else {
			licenseService.createLicense(license);
			Person person = license.getPerson();
			Long personID = person.getId();
			return "redirect:/persons/" + personID;
	
		}
	}
	
	@RequestMapping("/persons/{id}")
	public String viewPerson(@PathVariable("id")Long id, Model model) {
		Person person = licenseService.findPerson(id);
		License license = licenseService.findLicense(id);
		model.addAttribute("person", person);
		model.addAttribute("license", license);
		return "/viewPerson.jsp";
	}
	
}
