package com.example.prescripto.repository.doctor;

import com.example.prescripto.models.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

}
