package com.example.prescripto.models.junctionModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppointmentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientId;
    private String doctorId;
    private Long appointmentId;
    private Long payment_id;
    private String paymentMode; // Like "online", "cash" etc.

    public AppointmentPayment() {};

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
}
