package com.schoolproject.schoolproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolproject.schoolproject.entities.ReportCard;
import com.schoolproject.schoolproject.services.ReportCardService;

@RestController
@RequestMapping(value = "/reportcard")
public class ReportCardResource {
	@Autowired
	private ReportCardService reportCardService;
	
	@GetMapping
	public ResponseEntity<List<ReportCard>> findAll(){
		List<ReportCard> reportCards = reportCardService.findAll();
		return ResponseEntity.ok().body(reportCards);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ReportCard> findById(@PathVariable Long id) {
		ReportCard reportCard = reportCardService.findById(id);
		return ResponseEntity.ok().body(reportCard);
	}

}
