package com.schoolproject.schoolproject.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	
	@ElementCollection
	@MapKeyColumn(name="subject_id")
    @Column(name="subject_note")
	@CollectionTable(name="subject_note", joinColumns=@JoinColumn(name="subject_id"))
	private Map<Subject, Double> notes = new HashMap<>();
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	@JsonIgnore
	private Student student;
	
	public ReportCard() {
	}

	public ReportCard(Long id, String grade, Student student) {
		this.id = id;
		this.grade = grade;
		this.student = student;
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
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void setMedia() {
		Double sum = 0.0;
		for (Map.Entry<Subject, Double> n : notes.entrySet()) {
			sum+=n.getValue();
		}
		this.media = sum/notes.size();
	}


	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Map<Subject, Double> getNotes() {
		return notes;
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

	
}
