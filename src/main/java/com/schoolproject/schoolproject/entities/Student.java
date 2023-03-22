package com.schoolproject.schoolproject.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_student", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"})})
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant birthDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private Instant startDate; 
	private String phone;
	private String city;
	
	
	@ManyToMany(mappedBy = "students")
	@JsonIgnore
	private Set<SchoolClass> schoolClasses = new HashSet<>();
	
	@OneToMany(mappedBy = "student")
	private Set<ReportCard> reportCards = new HashSet<>();
	
	
	public Student() {
		
	}
	
	public Student(Long id, String name, String cpf, Instant birthDate, Instant startDate,  String phone, String city) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.phone = phone;
		this.city = city;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}
	

	public Set<ReportCard> getReportCards() {
		return reportCards;
	}


	


	public Set<SchoolClass> getSchoolClasses() {
		return schoolClasses;
	}

	public void setSchoolClasses(Set<SchoolClass> schoolClasses) {
		this.schoolClasses = schoolClasses;
	}


	public void isValidForms(){
		if (name == null || cpf == null || birthDate == null || startDate == null || phone == null || city == null) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Forms Null!");
		}
	}

	public void isValidCpf() {
		
		
		List<Integer> cpfList = Arrays.asList(cpf.split(""))
				.stream()
				.map(n -> Integer.valueOf(n))
				.toList();


		int firstDigit = getDigit(cpfList, 10, 2);;
		int secondDigit = getDigit(cpfList, 11, 1);

        if (!(firstDigit == cpfList.get(cpfList.size()-2) && secondDigit == cpfList.get(cpfList.size()-1))) {
    	    throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cpf Invalid!");
        }
	       
	}


	private Integer getDigit(List<Integer> cpf, Integer temp, Integer sizeCutCpf) {
        Integer sumDigit = 0;
        for (int i = 0; i < cpf.size()-sizeCutCpf; i++) {
            sumDigit += cpf.get(i) * temp;
            temp--;
        }
        int digit = (sumDigit * 10) % 11;

        if (digit == 10) {
            digit = 0;
        }
        return digit;
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
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birthDate=" + birthDate + ", startDate="
				+ startDate + ", phone=" + phone + ", city=" + city + "]";
	}

	
}
