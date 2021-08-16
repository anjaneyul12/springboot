package com.hospital.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hospital.api.entity.Patient;
import com.hospital.api.exception.UserNotFoundException;
import com.hospital.api.repository.PatientRepository;

@RestController
public class PatientController {
	
	@Autowired
	private PatientRepository service;

	@GetMapping("/patient/{id}")
	public EntityModel<Patient> retrivePatient(@PathVariable long id) {
		Optional<Patient> patient = service.findById(id);
		if (!patient.isPresent())
			throw new UserNotFoundException("Patient Not Found id is: " + id);

		EntityModel<Patient> model = EntityModel.of(patient.get());
		WebMvcLinkBuilder linkToPatients = linkTo(methodOn(this.getClass()).retriveAllPatient());
		model.add(linkToPatients.withRel("all-Patients"));

		return model;
	}

	@DeleteMapping("/patient/{id}")
	public void deletePatient(@PathVariable long id) {
		service.deleteById(id);
	}

	@GetMapping("/patients")
	public List<Patient> retriveAllPatient() {
		return service.findAll();
	}

	@PostMapping("/patient")
	public ResponseEntity<Object> createPatient(@Valid @RequestBody Patient patients) {
		Patient patient = service.save(patients);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/patient")
	public ResponseEntity<Object> updatePatient(@RequestBody Patient patients) {
		Patient patient = service.saveAndFlush(patients);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	


	
}
