package com.example.prescripto.models.patient;

import com.example.prescripto.models.junctionModel.Appointment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PatientInfo {
    @Id
    private String email;

    private String fullName;
    private String phone;
    private String addLine1;
    private String addLine2;
    private String gender;
    private LocalDate dob;
    private String profileImage;

    @OneToMany(mappedBy = "patientInfo")
    private List<Appointment> appointments;

    public PatientInfo() {};
    public PatientInfo(String email, String phone, String addLine1, String addLine2, String gender, LocalDate dob, String profileImage) {
        this.email = email;
        this.phone = phone;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.gender = gender;
        this.dob = dob;
        this.profileImage = profileImage;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
