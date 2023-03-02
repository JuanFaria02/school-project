package com.schoolproject.schoolproject.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.repositories.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception {
		Student student = new Student(null, "José Silva", "123456",
				new Date().from(Instant.now()),"21992402732", "São Gonçalo");
		
		Student student2 = new Student(null, "Maria Silva", "12356",
				new Date().from(Instant.now()),"21912402732", "São Gonçalo");
		
		studentRepository.saveAll(Arrays.asList(student, student2));
		
	}
	
}
