package com.schoolproject.schoolproject.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.schoolproject.schoolproject.entities.Employee;
import com.schoolproject.schoolproject.entities.Teacher;
import com.schoolproject.schoolproject.repositories.EmployeeRepository;
import com.schoolproject.schoolproject.services.exceptions.CpfInvalidException;
import com.schoolproject.schoolproject.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherService extends EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;

	public Teacher insert(Teacher employee) {
		isValidForms(employee);
		isValidCpf(employee.getCpf());
		return employeeRepository.save(employee);
	}


	public Employee update(Long id, Teacher employee) {
		Teacher entity = (Teacher) employeeRepository.getReferenceById(id);
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
	
	private void updateData(Teacher entity, Teacher employee) {
		entity.setName(employee.getName());
		entity.setCpf(employee.getCpf());
		entity.setCity(employee.getCity());
		entity.setPhone(employee.getPhone());
		entity.setBirthDate(employee.getBirthDate());
		entity.setStartDate(employee.getStartDate());
		entity.setSubject(employee.getSubject());
	}

	
	private void isValidForms(Teacher obj) {
		if (obj.getName() == null || 
				obj.getCpf() == null || 
				obj.getBirthDate() == null ||
				obj.getStartDate() == null || 
				obj.getPhone() == null || 
				obj.getCity() == null ||
				obj.getPositionCompany() == null ||
				obj.getType() == null ||
				obj.getSubject() == null) {
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
