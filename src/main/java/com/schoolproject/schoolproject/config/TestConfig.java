package com.schoolproject.schoolproject.config;

import java.time.Instant;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.repositories.SchoolClassRepository;
import com.schoolproject.schoolproject.repositories.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	@Override
	public void run(String... args){



		Student student = new Student(null, "Maria Silva",
				"235500332", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.now(), "21992314045", "São Gonçalo");
		
		
		SchoolClass sc1 = new SchoolClass(null, "901");
		Student student1 = new Student(null, "José Silva",
				"235501332", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.now(), "21992314045", "São Gonçalo");
	
		
		student1.setSchoolClass(sc1);
		student.setSchoolClass(sc1);
		sc1.getStudents().add(student1);
		sc1.getStudents().add(student);
		studentRepository.saveAll(Arrays.asList(student,student1));
		schoolClassRepository.saveAll(Arrays.asList(sc1));
		
	}
	
}
