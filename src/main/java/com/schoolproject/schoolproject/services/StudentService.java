package com.schoolproject.schoolproject.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.repositories.StudentRepository;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	public List<Student> findAll(){
		return studentRepository.findAll();
	}
	
	public Student findById(Long id) {
		Optional<Student> obj = studentRepository.findById(id);
		return obj.orElseThrow(()->new ResourceNotFoundException(id));
	}
	
	public Student insert(Student student) {
		return studentRepository.save(student);	
	}
	
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
	}
	
	public Student update(Long id, Student student) {
		Student entity = studentRepository.getReferenceById(id);
		updateData(entity, student);
		return studentRepository.save(entity);
	}
	
	private void updateData(Student entity, Student student) {
		entity.setName(student.getName());
		entity.setCpf(student.getCpf());
		entity.setCity(student.getCity());
		entity.setPhone(student.getPhone());
		entity.setBirthDate(student.getBirthDate());
		entity.setStartDate(student.getStartDate());

	}
	
}
