package com.timcoville.relationships.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.timcoville.relationships.models.License;
import com.timcoville.relationships.models.Person;
import com.timcoville.relationships.repositories.LicenseRepository;
import com.timcoville.relationships.repositories.PersonRepository;

@Service
public class LicenseService {
	private final LicenseRepository licenseRepo;
	private final PersonRepository personRepo;
	public LicenseService(LicenseRepository licenseRepo, PersonRepository personRepo) {
		this.licenseRepo = licenseRepo;
		this.personRepo = personRepo;
	}
	
	public List<Person> allPeople(){
		return personRepo.findAll();
	}
	
	public Person createPerson(Person record) {
		return personRepo.save(record);
	}
	
	public Person findPerson(Long id) {
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		} else {
			return null;
		}
		
	}
	
	public License createLicense(License record) {
		return licenseRepo.save(record);
	}
	
	public License findLicense(Long id) {
		return licenseRepo.findByPerson_id(id);
	}
	
	public List<License> allLicenses() {
		return licenseRepo.findAllByOrderByIdDesc();
	}
	
	
	
	
}
