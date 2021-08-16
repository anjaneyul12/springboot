package com.hospital.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
