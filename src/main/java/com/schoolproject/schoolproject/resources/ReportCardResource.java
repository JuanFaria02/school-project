package com.schoolproject.schoolproject.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping
	public ResponseEntity<ReportCard> insert(@Valid @RequestBody ReportCard reportCard){
		
		reportCard = reportCardService.insert(reportCard);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(reportCard.getId()).toUri();
		return ResponseEntity.created(uri).body(reportCard);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		reportCardService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ReportCard> update(@PathVariable Long id, @RequestBody ReportCard reportCard){
		reportCard = reportCardService.update(id, reportCard);
		return ResponseEntity.ok().body(reportCard);
	}
	
}
