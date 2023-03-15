package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.PositionCompany;
import com.schoolproject.schoolproject.services.PositionCompanyService;

@RestController
@RequestMapping(value = "/position-companies")
public class PositionCompanyResource {
	@Autowired
	private PositionCompanyService positionCompanyService;
	
	@GetMapping
	public ResponseEntity<List<PositionCompany>> findAll(){
		List<PositionCompany> positionCompanies = positionCompanyService.findAll();
		return ResponseEntity.ok().body(positionCompanies);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PositionCompany> findById(@PathVariable Long id){
		PositionCompany positionCompany = positionCompanyService.findById(id);
		return ResponseEntity.ok().body(positionCompany);
	}
}
