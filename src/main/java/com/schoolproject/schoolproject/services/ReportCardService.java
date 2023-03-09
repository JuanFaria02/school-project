package com.schoolproject.schoolproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.schoolproject.entities.ReportCard;
import com.schoolproject.schoolproject.repositories.ReportCardRepository;

@Service
public class ReportCardService {
	@Autowired
	private ReportCardRepository reportCardRepository;
	
	public List<ReportCard> findAll(){
		return reportCardRepository.findAll();
	}
	
	public ReportCard findById(Long id) {
		Optional<ReportCard> obj = reportCardRepository.findById(id);
		return obj.get();
	}
}
