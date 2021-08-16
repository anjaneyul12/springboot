package com.hospital.api.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.api.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
