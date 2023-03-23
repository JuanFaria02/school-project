package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.schoolproject.schoolproject.entities.Subject;
import com.schoolproject.schoolproject.repositories.SubjectRepository;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public List<Subject> findAll(){
		return subjectRepository.findAll();
	}
	
	public Subject findById(Long id) {
		Optional<Subject> obj = subjectRepository.findById(id);
		return obj.orElseThrow(()->new ResourceNotFoundException(id));
	}
	
	
	public Subject insert(Subject obj) {
		isValidForms(obj);
		return subjectRepository.save(obj);		
	}
	
	
	public void deleteById(Long id) { 
		try {
			findById(id);
			subjectRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Subject update(Long id, Subject obj) {
		Subject entity = subjectRepository.getReferenceById(id);
		try {
			isValidForms(obj);
			updateData(entity, obj);
			return subjectRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	private void updateData(Subject entity, Subject obj) {
		entity.setName(obj.getName());
		
	}
	
	private void isValidForms(Subject obj) {
		if(obj.getName() == null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Values null!");
		}
	}
}
