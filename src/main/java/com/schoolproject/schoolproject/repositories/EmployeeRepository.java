package com.schoolproject.schoolproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.schoolproject.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
