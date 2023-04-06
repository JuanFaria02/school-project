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
	
	@PostMapping
	public ResponseEntity<SchoolClass> insert(@Valid @RequestBody SchoolClass schoolClass){
		
		schoolClass = schoolClassService.insert(schoolClass);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(schoolClass.getId()).toUri();
		return ResponseEntity.created(uri).body(schoolClass);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		schoolClassService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SchoolClass> update(@PathVariable Long id, @RequestBody SchoolClass schoolClass){
		schoolClass = schoolClassService.update(id, schoolClass);
		return ResponseEntity.ok().body(schoolClass);
	}
	
	@PutMapping(value = "/{id}/students/{idStudent}")
	public ResponseEntity<SchoolClass> insertStudents(@PathVariable Long id, @PathVariable Long idStudent){
		SchoolClass schoolClass = schoolClassService.insertStudent(id, idStudent);
		return ResponseEntity.ok().body(schoolClass);
	}
	
	@PutMapping(value = "/{id}/teachers/{idTeacher}")
	public ResponseEntity<SchoolClass> insertTeacher(@PathVariable Long id, @PathVariable Long idTeacher){
		SchoolClass schoolClass = schoolClassService.insertTeacher(id, idTeacher);
		return ResponseEntity.ok().body(schoolClass);
	}
}

