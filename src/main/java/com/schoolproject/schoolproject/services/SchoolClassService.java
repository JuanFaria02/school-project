package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.repositories.SchoolClassRepository;

@Service
public class SchoolClassService {
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	
	public List<SchoolClass> findAll() {
		return schoolClassRepository.findAll();
	}
	
	public SchoolClass findById(Long id) {
		Optional<SchoolClass> obj = schoolClassRepository.findById(id);
		return obj.get();
	}
	
	
}

