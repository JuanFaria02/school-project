package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.PositionCompany;
import com.schoolproject.schoolproject.repositories.PositionCompanyRepository;

@Service
public class PositionCompanyService {
	@Autowired
	private PositionCompanyRepository positionCompanyRepository;
	
	public List<PositionCompany> findAll() {
		return positionCompanyRepository.findAll();
	}
	
	public PositionCompany findById(Long id) {
		Optional<PositionCompany> obj = positionCompanyRepository.findById(id);
		return obj.get();
	}
}
