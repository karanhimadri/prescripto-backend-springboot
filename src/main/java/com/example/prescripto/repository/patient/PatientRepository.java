package com.example.prescripto.repository.patient;

import com.example.prescripto.models.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
