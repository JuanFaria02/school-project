package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.Subject;
import com.schoolproject.schoolproject.services.SubjectService;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectResource {
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping
	public ResponseEntity<List<Subject>> findAll(){
		List<Subject> subjects = subjectService.findAll();
		return ResponseEntity.ok().body(subjects);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Subject> findById(@PathVariable Long id){
		Subject subject = subjectService.findById(id);
		return ResponseEntity.ok().body(subject);
	}
}
