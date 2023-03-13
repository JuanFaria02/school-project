package com.schoolproject.schoolproject.entities;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue(value = "T")
public class Teacher extends Employee{
	
	private String siape;


	public Teacher() {
	}

	public Teacher(Long id, String name, String cpf, Instant birthDate, Instant startDate, String phone, String city, String siape, String type) {
		super(id, name, cpf, birthDate, startDate, phone, city, type);

		this.siape = siape;
	
	}


	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}



	@Override
	public String toString() {
		return "Teacher [, siape=" + siape + "]";
	}
	
	
}
