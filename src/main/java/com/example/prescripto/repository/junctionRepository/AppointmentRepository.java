package com.example.prescripto.repository.junctionRepository;

import com.example.prescripto.models.junctionModel.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
