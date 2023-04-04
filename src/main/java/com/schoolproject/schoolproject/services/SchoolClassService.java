package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.repositories.SchoolClassRepository;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
	public SchoolClass insert(SchoolClass obj) {
		isValidForms(obj);
		return schoolClassRepository.save(obj);		
	}
	
	
	public void deleteById(Long id) { 
		try {
			findById(id);
			schoolClassRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		catch(NoSuchElementException e) {
			throw new ResourceNotFoundException(id);

		}
	}
	
	public SchoolClass update(Long id, SchoolClass obj) {
		SchoolClass entity = schoolClassRepository.getReferenceById(id);
		try {
			isValidForms(obj);
			updateData(entity, obj);
			return schoolClassRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(SchoolClass entity, SchoolClass obj) {
		entity.setGrade(obj.getGrade());
		entity.setMoment(obj.getMoment());
		entity.getTeachers().addAll(obj.getTeachers());
		entity.getStudents().addAll(obj.getStudents());
	}
	
	private void isValidForms(SchoolClass obj) {
		if(obj.getGrade() == null || obj.getMoment()==null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Values null!");
		}
	}
	
}

