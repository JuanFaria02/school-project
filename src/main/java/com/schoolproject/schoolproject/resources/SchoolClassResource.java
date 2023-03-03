package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.services.SchoolClassService;

@RestController
@RequestMapping(value = "/classes")
public class SchoolClassResource {
	@Autowired
	private SchoolClassService schoolClassService;
	
	@GetMapping
	public ResponseEntity<List<SchoolClass>> findAll() {
		List<SchoolClass> schoolClasses = schoolClassService.findAll();
		return ResponseEntity.ok().body(schoolClasses);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<SchoolClass> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(schoolClassService.findById(id));
	}
}

