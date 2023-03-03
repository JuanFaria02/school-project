package com.schoolproject.schoolproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_shcool_class")
public class SchoolClass implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String grade; 

	@Autowired
	@OneToMany
	Set<Student> students = new HashSet<>();
	
	

	public SchoolClass() {
	}



	public SchoolClass(Long id, String grade) {

		this.id = id;
		this.grade = grade;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public Set<Student> getStudents() {
		return students;
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
		SchoolClass other = (SchoolClass) obj;
		return Objects.equals(id, other.id);
	}



	@Override
	public String toString() {
		return "SchoolClass [id=" + id + ", grade=" + grade + ", students=" + students + "]";
	}


	
	

}
