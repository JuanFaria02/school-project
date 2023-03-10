package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.Teacher;
import com.schoolproject.schoolproject.repositories.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}
	
	public Teacher findById(Long id) {
		Optional<Teacher> obj = teacherRepository.findById(id);
		return obj.get();
	}
}
