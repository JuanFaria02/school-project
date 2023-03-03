package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.repositories.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	public List<Student> findAll(){
		return studentRepository.findAll();
	}
	
	public Student findById(Long id) {
		Optional<Student> obj = studentRepository.findById(id);
		return obj.get();
	}
}
