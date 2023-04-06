package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.schoolproject.schoolproject.entities.PositionCompany;
import com.schoolproject.schoolproject.repositories.PositionCompanyRepository;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PositionCompanyService {
	@Autowired
	private PositionCompanyRepository positionCompanyRepository;
	
	public List<PositionCompany> findAll() {
		return positionCompanyRepository.findAll();
	}
	
	public PositionCompany findById(Long id) {
		Optional<PositionCompany> obj = positionCompanyRepository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public PositionCompany insert(PositionCompany obj) {
		isValidForms(obj);
		return positionCompanyRepository.save(obj);		
	}
	
	
	public void deleteById(Long id) { 
		try {
			findById(id);
			positionCompanyRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public PositionCompany update(Long id, PositionCompany obj) {
		PositionCompany entity = positionCompanyRepository.getReferenceById(id);
		try {
			isValidForms(obj);
			updateData(entity, obj);
			return positionCompanyRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	
	private void updateData(PositionCompany entity, PositionCompany obj) {
		entity.setPositions(obj.getPositions());
		entity.setSalary(obj.getSalary());
	}
	
	private void isValidForms(PositionCompany obj) {
		if(obj.getPositions() == null || obj.getSalary()==null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Values null!");
		}
	}
}
