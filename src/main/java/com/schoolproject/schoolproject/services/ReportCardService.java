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

import com.schoolproject.schoolproject.entities.ReportCard;
import com.schoolproject.schoolproject.repositories.ReportCardRepository;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReportCardService {
	@Autowired
	private ReportCardRepository reportCardRepository;
	
	public List<ReportCard> findAll(){
		return reportCardRepository.findAll();
	}
	
	public ReportCard findById(Long id) {
		Optional<ReportCard> obj = reportCardRepository.findById(id);
		return obj.get();
	}
	
	public ReportCard insert(ReportCard obj) {
		isValidForms(obj);
		return reportCardRepository.save(obj);		
		
	}
	
	
	public void deleteById(Long id) { 
		try {
			findById(id);
			reportCardRepository.deleteById(id);
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
	
	public ReportCard update(Long id, ReportCard obj) {

		ReportCard entity = reportCardRepository.getReferenceById(id);
		try {
			isValidForms(obj);
			updateData(entity, obj);
			return reportCardRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(ReportCard entity, ReportCard obj) {
		entity.setGrade(obj.getGrade());
		entity.setMedia(obj.getMedia());
		entity.setStudent(obj.getStudent());
		entity.setSubject(obj.getSubject());
	}
	
	private void isValidForms(ReportCard obj) {
		if(obj.getGrade() == null || obj.getMedia() == null || obj.getStudent()==null || obj.getSubject()==null || obj.getStudent().getId() == null || obj.getSubject().getId()==null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Values null!");
		}
	}
}
