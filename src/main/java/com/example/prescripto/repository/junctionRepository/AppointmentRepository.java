package com.example.prescripto.repository.junctionRepository;

import com.example.prescripto.models.junctionModel.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    long countByDoctorEmailAndStatus(String doctorId, String status);

    long countByDoctorEmail(String docId);

    long countByDoctorEmailAndAppointmentDate(String doctorEmail, LocalDate appointmentDate);

    List<Appointment> findByDoctorEmailAndAppointmentDateBetween(String email, LocalDate startDate, LocalDate endDate);
}
