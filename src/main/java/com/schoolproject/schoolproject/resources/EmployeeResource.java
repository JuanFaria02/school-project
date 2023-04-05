package com.schoolproject.schoolproject.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.schoolproject.schoolproject.entities.Employee;
import com.schoolproject.schoolproject.services.EmployeeService;
import com.schoolproject.schoolproject.services.TeacherService;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TeacherService teacherService;
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		
		List<Employee> listEmployees = employeeService.findAll();
		return ResponseEntity.ok().body(listEmployees);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		
		Employee employee = employeeService.findById(id);
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping
	public ResponseEntity<Employee> insert(@RequestBody Employee employee){
		
		if (employee.getType().equals("E".toUpperCase())) {
			employee = employeeService.insert(employee);
		}
		else if(employee.getType().equals("T".toUpperCase())) {
			employee = teacherService.insert(employee);
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employee.getId()).toUri();
		
		return ResponseEntity.created(uri).body(employee);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		employeeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee){
		if (employee.getType().equals("E".toUpperCase())) {
			employee = employeeService.update(id, employee);
		}
		else if(employee.getType().equals("T".toUpperCase())) {
			employee = teacherService.update(id, employee);
		}		
		else {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Forms Null!");

		}
		return ResponseEntity.ok().body(employee);
	}
}
