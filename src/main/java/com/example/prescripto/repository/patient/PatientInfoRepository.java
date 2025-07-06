package com.example.prescripto.repository.patient;

import com.example.prescripto.models.patient.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, String> {

}
