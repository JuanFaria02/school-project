package com.schoolproject.schoolproject.config;

import java.time.Instant;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.schoolproject.schoolproject.entities.PositionCompany;
import com.schoolproject.schoolproject.entities.ReportCard;
import com.schoolproject.schoolproject.entities.SchoolClass;
import com.schoolproject.schoolproject.entities.Student;
import com.schoolproject.schoolproject.entities.Subject;
import com.schoolproject.schoolproject.entities.Teacher;
import com.schoolproject.schoolproject.repositories.EmployeeRepository;
import com.schoolproject.schoolproject.repositories.PositionCompanyRepository;
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
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PositionCompanyRepository positionCompanyRepository;
	@Override
	public void run(String... args){


		
		Student student = new Student(null, "Maria Silva",
				"235500332", Instant.now(),
				Instant.now(), "21992314045", "São Gonçalo");
		
		
		SchoolClass sc1 = new SchoolClass(null, "901", Instant.now());
		Student student1 = new Student(null, "José Silva",
				"235501332", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.parse("2004-06-20T19:53:07Z"), "21992314045", "São Gonçalo");
		Student student2 = new Student(null, "José Silva",
				"3244444211", Instant.parse("2004-06-20T19:53:07Z"),
				Instant.parse("2004-06-20T19:53:07Z"), "21992314045", "São Gonçalo");
		Subject s1 = new Subject(null, "Math");
		Subject s2 = new Subject(null, "Phisics");


		ReportCard rp = new ReportCard(null, "901", student, s1, 3.4);
		ReportCard rp1 = new ReportCard(null, "901", student, s2, 8.5);
		
		
		PositionCompany positionCompany = new PositionCompany(null, "Teacher", 3000.0);
		Teacher employee = new Teacher(null, "Maria Silva", 
				"235500332", Instant.parse("2004-06-20T19:53:07Z"),
		Instant.now(), "21992314045", "São Gonçalo", positionCompany, "32424244", s1,"T");
		
		
		studentRepository.saveAll(Arrays.asList(student,student1, student2));
		schoolClassRepository.saveAll(Arrays.asList(sc1));
		subjectRepository.saveAll(Arrays.asList(s1,s2));
		positionCompanyRepository.saveAll(Arrays.asList(positionCompany));
		
		employeeRepository.saveAll(Arrays.asList(employee));
		reportCardRepository.saveAll(Arrays.asList(rp, rp1));
		sc1.getStudents().add(student1);
		sc1.getStudents().add(student);
		
		employeeRepository.saveAll(Arrays.asList(employee));
		sc1.getTeachers().add(employee);
		schoolClassRepository.saveAll(Arrays.asList(sc1));

	}
	
}
