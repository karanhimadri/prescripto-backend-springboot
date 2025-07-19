package com.example.prescripto.models.junctionModel;

import com.example.prescripto.models.doctor.Doctor;
import com.example.prescripto.models.patient.PatientInfo;
import com.example.prescripto.models.payment.PaymentInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientInfo patientInfo;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentInfo paymentInfo;

    private LocalDate appointmentDate;
    private String appointmentTime;
    private String status; // "SCHEDULED", "CANCELED", or "COMPLETED"

    public Appointment() {}

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "payment: "+ paymentInfo.getAmount();
    }
}
