package com.schoolproject.schoolproject.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant moment;
	
	@ManyToMany
	@JoinTable(name = "tb_classes_students", joinColumns = @JoinColumn(name = "id_class"),
	inverseJoinColumns = @JoinColumn(name = "id_student"))
	private Set<Student> students = new HashSet<>();


	public SchoolClass() {
	}



	public SchoolClass(Long id, String grade, Instant moment) {

		this.id = id;
		this.grade = grade;
		this.moment = moment;
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



	public Instant getMoment() {
		return moment;
	}



	public void setMoment(Instant moment) {
		this.moment = moment;
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
