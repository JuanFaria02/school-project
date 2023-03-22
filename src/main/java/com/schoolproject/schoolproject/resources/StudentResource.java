package com.schoolproject.schoolproject.resources;

import java.net.URI;
import java.util.List;

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

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.services.StudentService;


@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		
		List<Student> listStudent = studentService.findAll();
		return ResponseEntity.ok().body(listStudent);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		
		Student student = studentService.findById(id);
		return ResponseEntity.ok().body(student);
	}
	
	@PostMapping
	public ResponseEntity<Student> insert(@RequestBody Student student){
		
		
		student = studentService.insert(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(student.getId()).toUri();
		return ResponseEntity.created(uri).body(student);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		studentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student){
		student = studentService.update(id, student);
		return ResponseEntity.ok().body(student);
	}
}
