package com.schoolproject.schoolproject.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "T")
public class Teacher extends Employee{
	

	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;

	@ManyToMany(mappedBy = "teachers")
	@JsonIgnore
	private Set<SchoolClass> schoolClasses = new HashSet<>();
	public Teacher() {
	}

	public Teacher(Long id, String name, String cpf, Instant birthDate, Instant startDate, String phone, String city, PositionCompany positionCompany, Subject subject, String type) {
		super(id, name, cpf, birthDate, startDate, phone, city, positionCompany, type);

		this.subject = subject;
	
	}


	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [subject=" + subject + ", schoolClasses=" + schoolClasses + "]";
	}

	
	
	
	
}
