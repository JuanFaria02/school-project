package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.repositories.StudentRepository;
import com.schoolproject.schoolproject.services.exceptions.CpfInvalidException;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		
		try {
			student.isValidForms();
			student.isValidCpf();
			return studentRepository.save(student);
			
		}
		catch (NumberFormatException e) {
			throw new CpfInvalidException("Unprocessable Entity! Cpf Invalid!");
		}
		catch (HttpClientErrorException e) {
			if(e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());

			}
			else {
				throw new CpfInvalidException("Unprocessable Entity! Cpf Invalid!");

			}
		}
	
	}
	
	public void deleteById(Long id) { 
		try {
			findById(id);
			studentRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Student update(Long id, Student student) {
		Student entity = studentRepository.getReferenceById(id);
		try {
			student.isValidForms();
			student.isValidCpf();
			updateData(entity, student);
			return studentRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (NumberFormatException e) {
			throw new CpfInvalidException("Unprocessable Entity! Cpf Invalid!");
		}
		catch (HttpClientErrorException e) {
			if(e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());

			}
			else {
				throw new CpfInvalidException("Unprocessable Entity! Cpf Invalid!");

			}
		}
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
	