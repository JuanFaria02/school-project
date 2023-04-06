package com.schoolproject.schoolproject.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<PositionCompany> insert(@Valid @RequestBody PositionCompany positionCompany){
		
		positionCompany = positionCompanyService.insert(positionCompany);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(positionCompany.getId()).toUri();
		return ResponseEntity.created(uri).body(positionCompany);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		positionCompanyService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PositionCompany> update(@PathVariable Long id, @RequestBody PositionCompany positionCompany){
		positionCompany = positionCompanyService.update(id, positionCompany);
		return ResponseEntity.ok().body(positionCompany);
	}

}
