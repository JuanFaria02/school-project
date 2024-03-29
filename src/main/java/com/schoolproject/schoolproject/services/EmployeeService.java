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

import com.schoolproject.schoolproject.entities.Employee;
import com.schoolproject.schoolproject.repositories.EmployeeRepository;
import com.schoolproject.schoolproject.services.exceptions.CpfInvalidException;
import com.schoolproject.schoolproject.services.exceptions.DatabaseException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findById(Long id) {
		Optional<Employee> obj = employeeRepository.findById(id);
		return obj.orElseThrow(()->new ResourceNotFoundException(id));
	}
	
	public Employee insert(Employee employee) {
		isValidForms(employee);
		isValidCpf(employee.getCpf());
		return employeeRepository.save(employee);
	}

	
	public void deleteById(Long id) { 
		try {
			findById(id);
			employeeRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Employee update(Long id, Employee employee) {
		Employee entity = employeeRepository.getReferenceById(id);
		try {
			isValidForms(employee);
			isValidCpf(employee.getCpf());
			updateData(entity, employee);
			return employeeRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Employee entity, Employee employee) {
		entity.setName(employee.getName());
		entity.setCpf(employee.getCpf());
		entity.setCity(employee.getCity());
		entity.setPhone(employee.getPhone());
		entity.setBirthDate(employee.getBirthDate());
		entity.setStartDate(employee.getStartDate());
	}

	
	private void isValidForms(Employee obj) {
		if (obj.getName() == null || 
				obj.getCpf() == null || 
				obj.getBirthDate() == null ||
				obj.getStartDate() == null || 
				obj.getPhone() == null || 
				obj.getCity() == null ||
				obj.getPositionCompany() == null ||
				obj.getType() == null) {
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
