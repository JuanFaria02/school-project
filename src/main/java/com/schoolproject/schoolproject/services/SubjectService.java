package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.Subject;
import com.schoolproject.schoolproject.repositories.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> findAll(){
		return subjectRepository.findAll();
	}
	
	public Subject findById(Long id) {
		Optional<Subject> obj = subjectRepository.findById(id);
		return obj.get();
	}
}
