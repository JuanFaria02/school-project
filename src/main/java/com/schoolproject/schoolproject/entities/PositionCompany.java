package com.schoolproject.schoolproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Table(name = "tb_position_company",uniqueConstraints = {@UniqueConstraint(columnNames = {"positions"})})
@Entity
public class PositionCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String positions;
	private Double salary;
	
	@OneToMany(mappedBy = "positionCompany")
	@JsonIgnore
	private Set<Employee> employees  = new HashSet<>();
	public PositionCompany() {
	}

	public PositionCompany(Long id, String positions, Double salary) {
		this.id = id;
		this.positions = positions;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionCompany other = (PositionCompany) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", positions=" + positions + ", salary=" + salary + "]";
	}
	
	
	
}
