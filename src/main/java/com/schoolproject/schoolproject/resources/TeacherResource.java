package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.Teacher;
import com.schoolproject.schoolproject.services.TeacherService;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherResource {
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<List<Teacher>> findAll(){
		List<Teacher> teachers = teacherService.findAll();
		return ResponseEntity.ok().body(teachers);
	}
	@GetMapping(value = "/{id}")
	private ResponseEntity<Teacher> findById(@PathVariable Long id) {
		Teacher teacher = teacherService.findById(id);
		return ResponseEntity.ok().body(teacher);
	}
}
