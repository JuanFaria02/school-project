package com.schoolproject.schoolproject.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		isValidForms(student);		
		isValidCpf(student.getCpf());
		return studentRepository.save(student);
			
	
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
			isValidForms(student);
			isValidCpf(student.getCpf());
			updateData(entity, student);
			return studentRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
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
	
	private void isValidForms(Student student){
		if (student.getName() == null || student.getCpf() == null || student.getBirthDate() == null || student.getStartDate() == null || student.getPhone() == null || student.getCity() == null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Forms Null!");
		}
	}
	
	private void isValidCpf(String cpf) {
		try {
			List<Integer> cpfList = Arrays.asList(cpf.split(""))
				.stream()
				.map(n -> Integer.valueOf(n))
				.toList();
		

			int firstDigit = getDigit(cpfList, 10, 2);;
			int secondDigit = getDigit(cpfList, 11, 1);

			if (!(firstDigit == cpfList.get(cpfList.size()-2) && secondDigit == cpfList.get(cpfList.size()-1))) {
    	    	throw new CpfInvalidException("Cpf Invalid!");
			}
		}
		catch (NumberFormatException e) {
			// TODO: handle exception
	    	throw new CpfInvalidException("Cpf Invalid!");
			
		}
	       
	}


	private Integer getDigit(List<Integer> cpf, Integer temp, Integer sizeCutCpf) {
        Integer sumDigit = 0;
        for (int i = 0; i < cpf.size()-sizeCutCpf; i++) {
            sumDigit += cpf.get(i) * temp;
            temp--;
        }
        int digit = (sumDigit * 10) % 11;

        if (digit == 10) {
            digit = 0;
        }
        return digit;
    }

	
	
}
	