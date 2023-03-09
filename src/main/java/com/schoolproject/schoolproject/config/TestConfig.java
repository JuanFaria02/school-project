package com.schoolproject.schoolproject.config;

import java.time.Instant;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.schoolproject.schoolproject.entities.ReportCard;
import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.entities.Subject;
import com.schoolproject.schoolproject.repositories.ReportCardRepository;
import com.schoolproject.schoolproject.repositories.SchoolClassRepository;
import com.schoolproject.schoolproject.repositories.StudentRepository;
import com.schoolproject.schoolproject.repositories.SubjectRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SchoolClassRepository schoolClassRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ReportCardRepository reportCardRepository;
	@Override
	public void run(String... args){



		Student student = new Student(null, "Maria Silva",
				"235500332", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.now(), "21992314045", "São Gonçalo");
		
		
		SchoolClass sc1 = new SchoolClass(null, "901");
		Student student1 = new Student(null, "José Silva",
				"235501332", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.now(), "21992314045", "São Gonçalo");
	
		Subject s1 = new Subject(null, "Math");
		Subject s2 = new Subject(null, "Phisics");


		
		student1.setSchoolClass(sc1);
		student.setSchoolClass(sc1);
		

		ReportCard rp = new ReportCard(null, "901", student, s1, 3.4);
		ReportCard rp1 = new ReportCard(null, "901", student, s2, 8.5);
		
	

		
		studentRepository.saveAll(Arrays.asList(student,student1));
		schoolClassRepository.saveAll(Arrays.asList(sc1));

		subjectRepository.saveAll(Arrays.asList(s1,s2));
	
		reportCardRepository.saveAll(Arrays.asList(rp, rp1));
	
	}
	
}
