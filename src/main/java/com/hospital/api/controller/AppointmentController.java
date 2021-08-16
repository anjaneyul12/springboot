package com.hospital.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hospital.api.entity.Appointment;
import com.hospital.api.entity.Patient;
import com.hospital.api.exception.UserNotFoundException;
import com.hospital.api.repository.AppointmentRepository;
import com.hospital.api.repository.PatientRepository;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository service;
	
	@Autowired
	private PatientRepository patientService;

	@GetMapping("/appointment/{id}")
	public EntityModel<Appointment> retriveAppointment(@PathVariable long id) {
		Optional<Appointment> appointment = service.findById(id);
		if (!appointment.isPresent())
			throw new UserNotFoundException("Appointment Not Found id is: " + id);

		EntityModel<Appointment> model = EntityModel.of(appointment.get());
		WebMvcLinkBuilder linkToAppointments = linkTo(methodOn(this.getClass()).retriveAllAppointment());
		model.add(linkToAppointments.withRel("all-Appointments"));

		return model;
	}

	@DeleteMapping("/appointment/{id}")
	public void deleteAppointment(@PathVariable long id) {
		service.deleteById(id);
	}

	@GetMapping("/appointments")
	public List<Appointment> retriveAllAppointment() {
		return service.findAll();
	}
	
	@GetMapping("/patient/{id}/appointment")
	public List<Appointment> retriveAllAppointment(@PathVariable long id) {
		Optional<Patient> patient = patientService.findById(id);
		if (!patient.isPresent())
			throw new UserNotFoundException("Patient Not Found id is: " + id);
		return patient.get().getAppointments();
	}

	@PostMapping("/patient/{id}/appointment")
	public ResponseEntity<Object> createAppointment(@PathVariable long id,@RequestBody Appointment appointments) {
		Optional<Patient> patient = patientService.findById(id);
		if (!patient.isPresent())
			throw new UserNotFoundException("Patient Not Found id is: " + id);
		
		Patient patient1=patient.get();
		
		appointments.setPatient(patient1);
		
		
		Appointment appointment = service.save(appointments);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appointments.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	


	
}
