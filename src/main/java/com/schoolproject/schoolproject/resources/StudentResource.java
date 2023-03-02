package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		
		List<Student> listStudent = studentService.findAll();
		return ResponseEntity.ok().body(listStudent);
	}
}
