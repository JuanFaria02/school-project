package com.schoolproject.schoolproject.entities;

import java.io.Serializable;


import java.util.Objects;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "tb_report_card")
public class ReportCard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String grade;
	private Double media;
	
	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;
	
	public ReportCard() {
	}

	public ReportCard(Long id, String grade, Student student, Subject subject, Double media) {
		this.id = id;
		this.grade = grade;
		this.student = student;
		this.subject = subject;
		this.media = media;
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
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
		ReportCard other = (ReportCard) obj;
		return Objects.equals(id, other.id);
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	
}
