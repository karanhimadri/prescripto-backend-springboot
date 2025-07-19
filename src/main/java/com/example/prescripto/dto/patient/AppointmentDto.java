package com.example.prescripto.dto.patient;

import java.time.LocalDate;

public class AppointmentDto {
    private Long appointmentId;
    private String image;
    private String doctorName;
    private String speciality;
    private Integer fees;
    private String addressLine1;
    private String addressLine2;
    private LocalDate appointmentDate;
    private String appointmentTime;
    private String paymentStatus;
    private String appointmentStatus;

    public AppointmentDto(Long appointmentId, String image, String doctorName, String speciality, Integer fees, String addressLine1, String addressLine2, LocalDate appointmentDate, String appointmentTime, String paymentStatus, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.image = image;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.fees = fees;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.paymentStatus = paymentStatus;
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getImage() {
        return image;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getFees() {
        return fees;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
